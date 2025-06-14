package com.kkimjinoh.movieadmin.domain.user.dto.response;

import com.kkimjinoh.movieadmin.domain.user.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 사용자 정보 ResponseDto
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 단일 조회 응답")
public class ResponseGetUserDto {

    @Schema(description = "사용자 ID", example = "1")
    private Long id;

    @Schema(description = "이메일", example = "example@hallym.ac.kr")
    private String email;

    @Schema(description = "사용자 역할", example = "ROLE_MANAGER")
    private UserRole userRole;

    @Schema(description = "소속 그룹 ID", example = "1")
    private Long userGroupId;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "학번", example = "202412345")
    private String studentNumber;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;

}
