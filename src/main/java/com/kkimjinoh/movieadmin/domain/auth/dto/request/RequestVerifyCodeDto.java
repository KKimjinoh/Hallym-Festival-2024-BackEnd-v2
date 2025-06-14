package com.kkimjinoh.movieadmin.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 이메일 인증 확인 RequestDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이메일 인증 확인 요청")
public class RequestVerifyCodeDto {

    @Schema(description = "이메일", example = "user@hallym.ac.kr")
    @NotBlank(message = "이메일은 비어 있을 수 없습니다.")
    private String email;

    @Schema(description = "인증코드", example = "A1B2C3")
    @NotBlank(message = "인증코드는 비어 있을 수 없습니다.")
    private String code;
}
