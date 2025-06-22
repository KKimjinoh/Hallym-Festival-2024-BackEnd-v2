package com.kkimjinoh.movieadmin.domain.reservation.mapper;

import com.kkimjinoh.movieadmin.domain.reservation.dto.request.RequestReservationDto;
import com.kkimjinoh.movieadmin.domain.reservation.dto.response.ResponseGetReservationDto;
import com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationEntity;
import com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationStatus;
import org.mapstruct.Mapper;

/**
 * Reservation Mapper
 */
@Mapper(componentModel = "spring")
public interface ReservationMapper {

    /**
     * reqReservationDto + (status, waitingNumber 포함) -> ReservationEntity
     */
    default ReservationEntity reqReservationDtoToReservationEntity(RequestReservationDto dto, ReservationStatus status, Integer waitingNumber) {
        return ReservationEntity.builder()
                .name(dto.getName())
                .studentNumber(dto.getStudentNumber())
                .phoneNumber(dto.getPhoneNumber())
                .peopleCount(dto.getPeopleCount())
                .privacyAgree(dto.isPrivacyAgree())
                .status(status)
                .waitingNumber(waitingNumber)
                .build();
    }

    /**
     * 예약 취소용 엔티티 생성 (상태를 CANCELED로 설정하고 소프트 삭제 처리)
     */
    default ReservationEntity cancelReservationEntity(ReservationEntity origin) {
        ReservationEntity canceled = origin.toBuilder()
                .status(ReservationStatus.CANCELED)
                .build();
        canceled.softDelete(); // DateEntity의 deletedAt 설정
        return canceled;
    }

    /**
     * 대기자 승격 → 상태 SUCCESS + waitingNumber null 설정
     */
    default ReservationEntity promoteWaiterToSuccess(ReservationEntity entity) {
        return entity.toBuilder()
                .status(ReservationStatus.SUCCESS)
                .waitingNumber(null)
                .build();
    }

    /**
     * // reservationEntity -> ResReservationDto
     */
    ResponseGetReservationDto reservationEntityToResReservationDto(ReservationEntity entity);
}