package com.kkimjinoh.movieadmin.domain.notice.dto.response;

import com.kkimjinoh.movieadmin.domain.notice.entity.NoticeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * 공지사항 단일 조회 ResponseDto
 */
@Getter
@Builder
@Schema(description = "공지사항 단일 조회 응답")
public class ResponseGetNoticeDto {

    @Schema(description = "공지사항 ID", example = "1")
    private Long id;

    @Schema(description = "공지사항 제목", example = "주점예약 공지")
    private String title;

    @Schema(description = "분실물 명", example = "공지에대한 내용 서술")
    private String content;

    @Schema(description = "생성일시", example = "2025-06-01T20:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시", example = "2025-06-01T20:05:00")
    private LocalDateTime updatedAt;

}
