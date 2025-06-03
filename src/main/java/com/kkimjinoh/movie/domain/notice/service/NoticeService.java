package com.kkimjinoh.movie.domain.notice.service;

import com.kkimjinoh.movie.domain.notice.dto.response.ResponseGetNoticesListDto;
import com.kkimjinoh.movie.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 공지사항 Service
 * 공지사항 조회 로직을 담당한다.
 */
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 전체 공지사항들을 조회한다.
     *
     * @return  ResponseGetNoticesListDto 존재하는 모든 공지사항 목록
     */
    @Transactional(readOnly = true)
    public ResponseGetNoticesListDto getNoticesList() {
        return ResponseGetNoticesListDto.fromEntities(noticeRepository.findAll());
    }
}
