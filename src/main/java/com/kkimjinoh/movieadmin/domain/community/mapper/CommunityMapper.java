package com.kkimjinoh.movieadmin.domain.community.mapper;

import com.kkimjinoh.movieadmin.domain.community.dto.request.RequestCreateMessageDto;
import com.kkimjinoh.movieadmin.domain.community.dto.response.ResponseGetMessageDto;
import com.kkimjinoh.movieadmin.domain.community.entity.CommunityEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * 커뮤니티 관련 매핑 처리 Mapper
 */
@Mapper(componentModel = "spring")
public interface CommunityMapper {

    /**
     * RequestCreateMessageDto -> CommunityEntity (비밀번호 제외)
     */
    CommunityEntity ReqCreateMessageDtoToCommunityEntity(RequestCreateMessageDto dto);

    /**
     * List<CommunityEntity> → List<ResponseGetMessageDto> 변환
     */
    List<ResponseGetMessageDto> CommunityEntitiesToResGetMessageDtos(List<CommunityEntity> entities);

    /**
     * CommunityEntity -> ResponseGetMessageDto
     */
    ResponseGetMessageDto CommunityEntityToResGetMessageDto(CommunityEntity entity);
}
