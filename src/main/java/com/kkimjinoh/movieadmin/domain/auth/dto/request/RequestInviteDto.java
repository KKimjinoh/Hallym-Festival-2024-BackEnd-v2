package com.kkimjinoh.movieadmin.domain.auth.dto.request;

import com.kkimjinoh.movieadmin.domain.user.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 사용자 초대 RequestDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 초대(등록) 요청 DTO")
public class RequestInviteDto {

    @Schema(description = "이메일", example = "example@hallym.ac.kr", required = true)
    @NotBlank(message = "이메일은 비어 있을 수 없습니다.")
    private String email;

    @Schema(description = "사용자 역할", example = "ROLE_MANAGER", required = true)
    @NotNull(message = "사용자 역할은 필수 입력 값입니다.")
    private UserRole userRole;

    @Schema(description = "소속 그룹 ID", example = "1", required = true)
    @NotNull(message = "소속 그룹 ID는 필수 입력 값입니다.")
    private Long userGroupId;
}

