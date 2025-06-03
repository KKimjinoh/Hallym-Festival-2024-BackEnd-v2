package com.kkimjinoh.movieadmin.domain.notice.dto.request;

import com.kkimjinoh.movieadmin.domain.notice.entity.NoticeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 공지사항 추가 RequestDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "분실물 추가 요청")
public class RequestCreateNoticeDto {

    @Schema(description = "공지사항 제목", example = "주점예약 공지")
    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    private String title;

    @Schema(description = "분실물 명", example = "공지에대한 내용 서술")
    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    private String content;

    /**
     * RequestCreateNoticeDto -> NoticeEntity
     */
    public NoticeEntity toEntity() {
        return NoticeEntity.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
