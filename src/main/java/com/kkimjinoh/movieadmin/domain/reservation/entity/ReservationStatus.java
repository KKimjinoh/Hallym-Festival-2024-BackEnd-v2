package com.kkimjinoh.movieadmin.domain.reservation.entity;

/**
 * 예약 상태를 나타내는 Enum
 */
public enum ReservationStatus {
    // 예약 성공
    SUCCESS,
    // 예약 대기 중
    WAITING,
    // 중복 예약
    DUPLICATE,
    // 예약 취소
    CANCELED,
    // 예약 실패
    FAIL
}