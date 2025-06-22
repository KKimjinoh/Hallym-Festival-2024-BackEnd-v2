
package com.kkimjinoh.movieadmin.domain.reservation.repository;

import com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationEntity;
import com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {


    /**
     * 승격 대상 대기자 조회
     * - 동일 학번 + 당일 생성된 예약만 조회
     * - 하루 기준 승격자 중복 방지 목적
     *
     * @param studentNumber 학번
     * @param start         조회 시작일 (당일 00:00)
     * @param end           조회 종료일 (당일 23:59:59.999)
     * @return Optional<ReservationEntity>
     */
    @Query("select r from ReservationEntity r where r.studentNumber = :sn and r.createdAt between :s and :e")
    Optional<ReservationEntity> findByStudentNumberAndCreatedAt(@Param("sn") String studentNumber,
                                                                @Param("s") LocalDateTime start,
                                                                @Param("e") LocalDateTime end);

    /**
     * 특정 상태(SUCCESS, WAITING 등)에 해당하는 예약 전체 조회
     *
     * @param statuses 상태 목록
     * @return 예약 목록
     */
    List<ReservationEntity> findAllByStatusIn(List<ReservationStatus> statuses);
}

