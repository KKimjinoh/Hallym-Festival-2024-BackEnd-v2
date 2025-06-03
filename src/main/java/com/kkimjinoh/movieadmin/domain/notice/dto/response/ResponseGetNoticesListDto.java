package com.kkimjinoh.movieadmin.domain.notice.dto.response;

import com.kkimjinoh.movieadmin.domain.notice.entity.NoticeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

/**
 * 공지사항 목록 조회 ResponseDto
 */
@Getter
@Builder
@Schema(description = "공지사항 목록 응답")
public class ResponseGetNoticesListDto {
    @Schema(description = "공지사항 목록")
    private List<ResponseGetNoticeDto> notices;

    public static ResponseGetNoticesListDto fromEntities(List<NoticeEntity> list) {
        return ResponseGetNoticesListDto.builder()
                .notices(
                        list.stream()
                                .map(ResponseGetNoticeDto::fromEntity)
                                .toList()
                )
                .build();
    }
}
