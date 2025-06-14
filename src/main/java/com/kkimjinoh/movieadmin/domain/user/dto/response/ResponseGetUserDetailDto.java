package com.kkimjinoh.movieadmin.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 사용자 상세 정보 ResponseDto
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 상세 조회 응답")
public class ResponseGetUserDetailDto extends ResponseGetUserDto {

    @Schema(description = "개인정보 수집 동의", example = "true")
    private boolean privacyAgree;

    @Schema(description = "알림 수신 동의", example = "true")
    private boolean pushAgree;

    @Schema(description = "최근 로그인 시각", example = "2025-06-05T21:00:00")
    private LocalDateTime loginDate;

    @Schema(description = "생성일시", example = "2025-06-01T20:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시", example = "2025-06-01T20:05:00")
    private LocalDateTime updatedAt;

}
