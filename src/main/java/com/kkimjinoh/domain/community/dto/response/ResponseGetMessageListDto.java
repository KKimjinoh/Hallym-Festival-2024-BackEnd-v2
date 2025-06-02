package com.kkimjinoh.domain.community.dto.response;

import com.kkimjinoh.domain.community.entity.CommunityEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

/**
 * 커뮤니티 글 목록 조회 ResponseDto
 */
@Getter
@Builder
@Schema(description = "커뮤니티 글 목록 응답")
public class ResponseGetMessageListDto {
    @Schema(description = "글 목록")
    private List<ResponseGetMessageDto> messageList;

    public static ResponseGetMessageListDto fromEntities(List<CommunityEntity> list) {
        return ResponseGetMessageListDto.builder()
                .messageList(
                        list.stream()
                                .map(ResponseGetMessageDto::fromEntity)
                                .toList()
                )
                .build();
    }
}
