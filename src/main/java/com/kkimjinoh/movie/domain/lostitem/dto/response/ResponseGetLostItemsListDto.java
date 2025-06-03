package com.kkimjinoh.movie.domain.lostitem.dto.response;

import com.kkimjinoh.movie.domain.lostitem.entity.LostItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 분실물 목록 조회 ResponseDto
 */
@Getter
@Builder
@Schema(description = "분실물 목록 응답")
public class ResponseGetLostItemsListDto {
    @Schema(description = "분실물 목록")
    private List<ResponseGetLostItemDto> lostItems;

    public static ResponseGetLostItemsListDto fromEntities(List<LostItemEntity> list) {
        return ResponseGetLostItemsListDto.builder()
                .lostItems(
                        list.stream()
                                .map(ResponseGetLostItemDto::fromEntity)
                                .toList()
                )
                .build();
    }
}
