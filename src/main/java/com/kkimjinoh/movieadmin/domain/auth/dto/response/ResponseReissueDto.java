package com.kkimjinoh.movieadmin.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Access 토큰 재발급 ResponseDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Access 토큰 재발급 응답 DTO")
public class ResponseReissueDto {

    @Schema(description = "Access 토큰(만료 가능)", example = "eyJhbGciOiJIUzI1NiIsInR...")
    private String accessToken;

}