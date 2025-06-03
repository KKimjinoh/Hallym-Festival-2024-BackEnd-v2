package com.kkimjinoh.movie.domain.community.dto.response;

import com.kkimjinoh.movie.domain.community.entity.CommunityEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * 커뮤니티 글 단일 조회 ResponseDto
 */
@Getter
@Builder
@Schema(description = "커뮤니티 글 단건 조회 응답")
public class ResponseGetMessageDto {

    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "글 내용", example = "안녕하세요!")
    private String content;

    @Schema(description = "닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "생성일시", example = "2025-06-01T20:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시", example = "2025-06-01T20:05:00")
    private LocalDateTime updatedAt;

    /**
     * CommunityEntity -> ResponseGetMessageDto
     */
    public static ResponseGetMessageDto fromEntity(CommunityEntity e) {
        return ResponseGetMessageDto.builder()
                .id(e.getId())
                .content(e.getContent())
                .nickname(e.getNickname())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}
