package com.kkimjinoh.movieadmin.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Access 토큰 재발급 RequestDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "토큰 재발급 요청")
public class RequestReissueDto {

    @Schema(description = "Access 토큰(만료 가능)", example = "eyJhbGciOiJIUzI1NiIsInR...")
    @NotBlank(message = "Access 토큰은 비어 있을 수 없습니다.")
    private String accessToken;

    @Schema(description = "Refresh 토큰", example = "eyJhbGciOiJIUzI1NiIsInR...")
    @NotBlank(message = "Refresh 토큰은 비어 있을 수 없습니다.")
    private String refreshToken;
}
