package com.kkimjinoh.movieadmin.domain.notice.service;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.movieadmin.domain.notice.dto.request.RequestCreateNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.dto.request.RequestUpdateNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.dto.response.ResponseGetNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.entity.NoticeEntity;
import com.kkimjinoh.movieadmin.domain.notice.mapper.NoticeMapper;
import com.kkimjinoh.movieadmin.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 공지사항 Service
 * 공지사항 추가, 조회, 수정, 삭제 로직을 담당한다.
 */
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;
    private final NoticeRepository noticeRepository;

    /**
     * 새로운 공지사항을 추가한다.
     *
     * @param body 공지사항 정보
     * @return ResponseGetNoticeDto 생성된 공지사항의 상세 정보
     */
    @Transactional
    public ResponseGetNoticeDto createNotice(RequestCreateNoticeDto body) {
        NoticeEntity entity = noticeMapper.reqCreateDtoToNoticeEntity(body);
        NoticeEntity saved = noticeRepository.save(entity);
        return noticeMapper.noticeEntityToResGetNoticeDto(saved);
    }

    /**
     * 공지사항을 수정한다.
     *
     * @param id 공지사항 id
     * @param body 공지사항 정보
     * @return ResponseGetNoticeDto 수정된 공지사항의 상세 정보
     */
    @Transactional
    public ResponseGetNoticeDto updateNotice(Long id, RequestUpdateNoticeDto body) {
        NoticeEntity entity = noticeRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.NOTICE_NOT_FOUND));

        NoticeEntity updated = noticeMapper.reqUpdateDtoToNoticeEntity(body, entity);
        return noticeMapper.noticeEntityToResGetNoticeDto(updated);
    }

    /**
     * 전체 공지사항들을 조회한다.
     *
     * @return  ResponseGetNoticesListDto 존재하는 모든 공지사항 목록
     */
    @Transactional(readOnly = true)
    public List<ResponseGetNoticeDto> getNoticesList() {
        return noticeMapper.noticeEntitiesToResGetNoticeDtos(noticeRepository.findAll());
    }

    /**
     * 지정된 ID의 공지사항을 삭제한다.
     *
     * @param id 삭제 대상 공지사항의 ID
     * @return StatusOkResponseDto 성공(OK) 응답
     */
    @Transactional
    public StatusOkResponseDto deleteNotice(Long id) {
        noticeRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.NOTICE_NOT_FOUND));

        noticeRepository.deleteById(id);
        return new StatusOkResponseDto();
    }
}
