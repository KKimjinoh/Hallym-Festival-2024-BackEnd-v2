package com.kkimjinoh.movie.domain.notice.controller;

import com.kkimjinoh.movie.domain.notice.docs.GetNoticesListDoc;
import com.kkimjinoh.movie.domain.notice.dto.response.ResponseGetNoticesListDto;
import com.kkimjinoh.movie.domain.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 공지사항 Controller
 * 공지사항 조회 기능을 담당한다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
@Tag(name = "공지사항", description = "공지사항 조회 기능")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    @GetNoticesListDoc
    public ResponseEntity<ResponseGetNoticesListDto> getNoticesList() {
        return ResponseEntity.ok(noticeService.getNoticesList());
    }
}
