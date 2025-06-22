package com.kkimjinoh.movieadmin.domain.reservation.dto.response;

import com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 예약 단건 조회 / 예약 결과 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "예약 결과 DTO")
public class ResponseGetReservationDto {

    @Schema(description = "예약 ID", example = "1")
    private Long id;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "학번", example = "202412345")
    private String studentNumber;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;

    @Schema(description = "인원수", example = "4")
    private int peopleCount;

    @Schema(description = "예약 상태", example = "SUCCESS")
    private ReservationStatus status;

    @Schema(description = "대기 번호", example = "13", nullable = true)
    private Integer waitingNumber;

    @Schema(description = "개인정보 수집 동의 여부", example = "true")
    private boolean privacyAgree;

    @Schema(description = "생성일시", example = "2025-06-01T20:00:00")
    private LocalDateTime createdAt;
}