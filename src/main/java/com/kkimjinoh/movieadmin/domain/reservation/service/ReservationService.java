package com.kkimjinoh.movieadmin.domain.reservation.service;

import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.global.redis.support.LuaExecutor;
import com.kkimjinoh.global.redis.support.RedisKeyUtils;
import com.kkimjinoh.movieadmin.domain.reservation.dto.request.RequestReservationDto;
import com.kkimjinoh.movieadmin.domain.reservation.dto.response.ResponseGetReservationDto;
import com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationEntity;
import com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationStatus;
import com.kkimjinoh.movieadmin.domain.reservation.mapper.ReservationMapper;
import com.kkimjinoh.movieadmin.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationStatus.SUCCESS;
import static com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationStatus.WAITING;

/**
 * 예약 Service (Redis Lua 기반 동시성 보장)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final LuaExecutor luaExecutor;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final RedisTemplate<String, String> redisTemplate;


    /**
     * 예약 전체 조회 (최대 200건)
     * - 상태가 SUCCESS 또는 WAITING인 예약만 조회
     * - SUCCESS 예약자를 우선 정렬 후,
     *   WAITING은 waitingNumber(대기 순서) 기준으로 정렬
     *
     * @return List<ResponseGetReservationDto> (최대 200건)
     */
    @Transactional(readOnly = true)
    public List<ResponseGetReservationDto> getAllReservations() {
        return reservationRepository.findAllByStatusIn(List.of(SUCCESS, WAITING)).stream()
                .sorted((a, b) -> {
                    // SUCCESS 우선 정렬, 그다음 WAITING은 waitingNumber 기준
                    if (a.getStatus() != b.getStatus()) {
                        return a.getStatus() == SUCCESS ? -1 : 1;
                    }
                    if (a.getStatus() == WAITING && a.getWaitingNumber() != null && b.getWaitingNumber() != null) {
                        return Integer.compare(a.getWaitingNumber(), b.getWaitingNumber());
                    }
                    return 0;
                })
                .limit(200)
                .map(reservationMapper::reservationEntityToResReservationDto)
                .toList();
    }


    /**
     * 선착순 예약 신청 처리
     * - Redis Lua를 사용해 중복 및 선착순 여부를 원자적으로 판단
     * - 예약 성공, 대기 등록, 중복 등 상태 판단 후 DB에 저장
     */
    @Transactional
    public ResponseGetReservationDto reserve(RequestReservationDto body) {
        LocalDate today = LocalDate.now();
        String reservationKey = RedisKeyUtils.reservationKey(today);
        String waitlistKey    = RedisKeyUtils.waitlistKey(today);

        // Lua 스크립트를 통한 원자적 예약 처리
        long result = luaExecutor.reserve(reservationKey, waitlistKey, body.getStudentNumber());

        ReservationStatus status;
        Integer waitingNumber = null;

        switch ((int) result) {
            // 이미 신청함
            case 0 -> status = ReservationStatus.DUPLICATE;
            // 예약 성공
            case 1 -> status = SUCCESS;
            case 2 -> {
                // 대기 등록
                status = WAITING;
                // 대기 순번 계산 (ZSET에서 순위 조회)
                Long rank = redisTemplate.opsForZSet().rank(waitlistKey, body.getStudentNumber());
                waitingNumber = rank == null ? null : rank.intValue() + 1;
            }
            // 기타 실패
            default -> status = ReservationStatus.FAIL;
        }

        // reqReservationDto + (status, waitingNumber 포함) -> ReservationEntity
        ReservationEntity entity = reservationMapper.reqReservationDtoToReservationEntity(body, status, waitingNumber);
        ReservationEntity saved = reservationRepository.save(entity);

        // reservationEntity -> ResReservationDto
        return reservationMapper.reservationEntityToResReservationDto(saved);
    }


    /**
     * 예약 취소 및 승격 처리
     * - Redis에서 예약자 제거 및 대기자 승격을 Lua로 처리 (원자적)
     * - DB에서 예약 상태를 CANCELED로 바꾸고, soft delete
     * - 승격된 대기자는 DB에서 상태 SUCCESS로 갱신
     */
    @Transactional
    public void cancel(Long reservationId) {
        // 1. 예약 엔티티 조회 (존재하지 않으면 예외)
        ReservationEntity target = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new DomainException(DomainError.RESERVATION_NOT_FOUND));

        if (target.getStatus() == ReservationStatus.CANCELED) {
            throw new DomainException(DomainError.RESERVATION_ALREADY_CANCELED);
        }

        LocalDate today = LocalDate.now();
        String reservationKey = RedisKeyUtils.reservationKey(today);
        String waitlistKey = RedisKeyUtils.waitlistKey(today);

        // 2. Redis에서 예약자 제거 및 대기자 승격
        String promoted = luaExecutor.cancel(reservationKey, waitlistKey, target.getStudentNumber());
        log.info("Redis 승격된 학번: '{}'", promoted);

        // 3. 대상 예약 엔티티를 취소 처리 (CANCELED + deletedAt) → Mapper 사용
        ReservationEntity canceled = reservationMapper.cancelReservationEntity(target);
        reservationRepository.save(canceled);

        // 4. 승격된 대기자가 있다면, DB에서 찾아 상태를 SUCCESS로 변경
        if (promoted != null && !promoted.isEmpty()) {
            reservationRepository.findByStudentNumberAndCreatedAt(
                    promoted,
                    today.atStartOfDay(),
                    today.plusDays(1).atStartOfDay().minusNanos(1)
            ).ifPresent(entity -> {
                ReservationEntity updated = reservationMapper.promoteWaiterToSuccess(entity);
                reservationRepository.save(updated);
                log.info("DB에서 승격 대상 처리: ID={}, 학번={}", updated.getId(), updated.getStudentNumber());
            });
        }
    }

}


