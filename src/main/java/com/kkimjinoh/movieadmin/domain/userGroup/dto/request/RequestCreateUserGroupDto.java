package com.kkimjinoh.movieadmin.domain.userGroup.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 사용자 그룹 생성 RequestDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "사용자 그룹 생성 요청")
public class RequestCreateUserGroupDto {

    @Schema(description = "사용자 그룹명", example = "한림대학교 총학생회")
    @NotBlank(message = "사용자 그룹명은 비어 있을 수 없습니다.")
    private String name;

}
