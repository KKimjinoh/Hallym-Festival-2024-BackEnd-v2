package com.kkimjinoh.movieadmin.domain.notice.controller;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.notice.docs.CreateNoticeDoc;
import com.kkimjinoh.movieadmin.domain.notice.docs.DeleteNoticeDoc;
import com.kkimjinoh.movieadmin.domain.notice.docs.GetNoticesListDoc;
import com.kkimjinoh.movieadmin.domain.notice.docs.UpdateNoticeDoc;
import com.kkimjinoh.movieadmin.domain.notice.dto.request.RequestCreateNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.dto.request.RequestUpdateNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.dto.response.ResponseGetNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 공지사항 Controller
 * 공지사항 조회, 추가, 수정, 삭제 기능을 담당한다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
@Tag(name = "공지사항", description = "공지사항 관리 기능")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    @CreateNoticeDoc
    public ResponseEntity<ResponseGetNoticeDto> createNotice(
            @Valid @RequestBody RequestCreateNoticeDto body) {
        return ResponseEntity.ok(noticeService.createNotice(body));
    }

    @GetMapping
    @GetNoticesListDoc
    public ResponseEntity<List<ResponseGetNoticeDto>> getNoticesList() {
        return ResponseEntity.ok(noticeService.getNoticesList());
    }

    @PutMapping("/{id}")
    @UpdateNoticeDoc
    public ResponseEntity<ResponseGetNoticeDto> updateNotice(
            @PathVariable("id") Long id,
            @RequestBody RequestUpdateNoticeDto body) {
        return ResponseEntity.ok(noticeService.updateNotice(id, body));
    }

    @DeleteMapping("/{id}")
    @DeleteNoticeDoc
    public ResponseEntity<StatusOkResponseDto> deleteNotice(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(noticeService.deleteNotice(id));
    }
}
