package com.kkimjinoh.movieadmin.domain.lostitem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 분실물 단일 조회 ResponseDto
 */
@Getter
@Builder
@Schema(description = "분실물 단일 조회 응답")
public class ResponseGetLostItemDto {

    @Schema(description = "분실물 ID", example = "1")
    private Long id;

    @Schema(description = "분실물 명", example = "파랑색 지갑")
    private String name;

    @Schema(description = "발견장소", example = "학교 운동장")
    private String location;

    @Schema(description = "분실물 사진 Url", example = "s3://이미지 주소")
    private String imageUrl;

    @Schema(description = "회수여부", example = "false")
    private boolean isReturn;

    @Schema(description = "생성일시", example = "2025-06-01T20:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시", example = "2025-06-01T20:05:00")
    private LocalDateTime updatedAt;

}
