package com.kkimjinoh.movieadmin.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 이메일 인증 확인 ResponseDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이메일 인증 확인 요청")
public class ResponseVerifyCodeDto {

    @Schema(description = "이메일", example = "user@hallym.ac.kr")
    private String email;

    @Schema(description = "회원가입 완료를 위한 임시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR...")
    private String signupToken;

}
