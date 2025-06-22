package com.kkimjinoh.movieadmin.domain.notice.mapper;

import com.kkimjinoh.movieadmin.domain.notice.dto.request.RequestCreateNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.dto.request.RequestUpdateNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.dto.response.ResponseGetNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.entity.NoticeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * 공지사항 Mapper
 */
@Mapper(componentModel = "spring")
public interface NoticeMapper {

    /**
     * RequestCreateNoticeDto -> NoticeEntity
     */
    NoticeEntity reqCreateDtoToNoticeEntity(RequestCreateNoticeDto dto);

    /**
     * RequestUpdateNoticeDto -> 기존 NoticeEntity
     */
    default NoticeEntity reqUpdateDtoToNoticeEntity(RequestUpdateNoticeDto dto, NoticeEntity entity) {
        return entity.toBuilder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    /**
     * NoticeEntity -> ResponseGetNoticeDto
     */
    ResponseGetNoticeDto noticeEntityToResGetNoticeDto(NoticeEntity entity);

    /**
     * List<NoticeEntity> -> List<ResponseGetNoticeDto>
     */
    List<ResponseGetNoticeDto> noticeEntitiesToResGetNoticeDtos(List<NoticeEntity> entities);
}
