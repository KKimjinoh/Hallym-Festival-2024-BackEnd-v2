package com.kkimjinoh.movieadmin.domain.auth.dto.response;

import com.kkimjinoh.movieadmin.domain.user.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 사용자 초대 ResponseDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 초대(등록) 응답 DTO")
public class ResponseInviteDto {

    @Schema(description = "사용자 ID", example = "1")
    private Long id;

    @Schema(description = "이메일", example = "example@hallym.ac.kr")
    private String email;

    @Schema(description = "사용자 역할", example = "ROLE_MANAGER")
    private UserRole userRole;

    @Schema(description = "소속 그룹 ID", example = "1")
    private Long userGroupId;
}
