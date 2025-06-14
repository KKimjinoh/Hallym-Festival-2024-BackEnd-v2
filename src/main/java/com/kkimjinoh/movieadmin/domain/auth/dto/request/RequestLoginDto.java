package com.kkimjinoh.movieadmin.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 로그인 RequestDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "로그인 요청")
public class RequestLoginDto {

    @Schema(description = "이메일", example = "sunwoo11228@gmail.com")
    @NotBlank(message = "이메일은 비어 있을 수 없습니다.")
    private String email;

    @Schema(description = "비밀번호", example = "admin1234")
    @NotBlank(message = "비밀번호는 비어 있을 수 없습니다.")
    private String password;
}
