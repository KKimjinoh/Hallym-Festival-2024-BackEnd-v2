package com.kkimjinoh.movieadmin.domain.lostitem.mapper;

import com.kkimjinoh.movieadmin.domain.lostitem.dto.request.RequestCreateLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.request.RequestUpdateLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.response.ResponseGetLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.entity.LostItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * 분실물 Mapper
 */
@Mapper(componentModel = "spring")
public interface LostItemMapper {

    /**
     * RequestCreateLostItemDto + imageUrl -> LostItemEntity 변환
     */
    LostItemEntity reqCreateDtoToLostItemEntity(RequestCreateLostItemDto dto, String imageUrl);

    /**
     * LostItemEntity -> ResponseGetLostItemDto
     */
    ResponseGetLostItemDto lostItemEntityToResDto(LostItemEntity entity);

    /**
     * List<LostItemEntity> -> List<ResponseGetLostItemDto>
     */
    List<ResponseGetLostItemDto> lostItemEntitiesToResDtos(List<LostItemEntity> entities);

    /**
     * RequestUpdateLostItemDto + imageUrl -> LostItemEntity
     */
    default LostItemEntity reqUpdateDtoToLostItemEntity(RequestUpdateLostItemDto dto, LostItemEntity entity, String imageUrl) {
        return entity.toBuilder()
                .name(dto.getName())
                .location(dto.getLocation())
                .imageUrl(imageUrl)
                .isReturn(dto.isReturn())
                .build();
    }
}
