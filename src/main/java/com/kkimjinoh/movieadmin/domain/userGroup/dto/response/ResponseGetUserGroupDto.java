package com.kkimjinoh.movieadmin.domain.userGroup.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * 유저그룹 단일 조회 ResponseDto
 */
@Getter
@Builder
@Schema(description = "커뮤니티 글 단건 조회 응답")
public class ResponseGetUserGroupDto {

    @Schema(description = "사용자 그룹 ID", example = "1")
    private Long id;

    @Schema(description = "사용자 그룹명", example = "한림대학교 총학생회")
    private String name;

    @Schema(description = "생성일시", example = "2025-06-01T20:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시", example = "2025-06-01T20:05:00")
    private LocalDateTime updatedAt;

}
