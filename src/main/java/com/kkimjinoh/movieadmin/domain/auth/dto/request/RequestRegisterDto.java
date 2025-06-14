package com.kkimjinoh.movieadmin.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 사용자 초대 RequestDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 초대 요청")
public class RequestRegisterDto {

    @Schema(description = "회원가입 완료를 위한 임시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR...")
    @NotBlank(message = "임시 토큰은 비어 있을 수 없습니다.")
    private String signupToken;

    @Schema(description = "비밀번호 (BCrypt 암호화)", example = "admin1234")
    @NotBlank(message = "비밀번호는 비어 있을 수 없습니다.")
    private String password;

    @Schema(description = "이름", example = "홍길동")
    @NotBlank(message = "이름은 비어 있을 수 없습니다.")
    private String name;

    @Schema(description = "학번", example = "202412345")
    @NotBlank(message = "학번은 비어 있을 수 없습니다.")
    private String studentNumber;

    @Schema(description = "휴대폰 번호", example = "01012345678")
    @NotBlank(message = "휴대폰 번호는 비어 있을 수 없습니다.")
    private String phoneNumber;

    @Schema(description = "개인정보 수집 동의 여부", example = "true")
    private boolean privacyAgree;

    @Schema(description = "푸시 알림 동의 여부", example = "false")
    private boolean pushAgree;
}
