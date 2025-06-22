package com.kkimjinoh.movieadmin.domain.reservation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * 예약 요청 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "예약 요청 DTO")
public class RequestReservationDto {

    @Schema(description = "이름", example = "홍길동", required = true)
    @NotBlank(message = "이름은 비어 있을 수 없습니다.")
    private String name;

    @Schema(description = "학번", example = "202412345", required = true)
    @NotBlank(message = "학번은 비어 있을 수 없습니다.")
    private String studentNumber;

    @Schema(description = "전화번호", example = "010-1234-5678", required = true)
    @NotBlank(message = "전화번호는 비어 있을 수 없습니다.")
    private String phoneNumber;

    @Schema(description = "인원수", example = "4", required = true)
    @Min(value = 1, message = "인원수는 1명 이상이어야 합니다.")
    private int peopleCount;

    @Schema(description = "개인정보 수집 동의 여부", example = "true", required = true)
    @AssertTrue(message = "개인정보 수집에 동의해야 합니다.")
    private boolean privacyAgree;
}