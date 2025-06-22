package com.kkimjinoh.movieadmin.domain.community.controller;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.community.docs.CreateMessageDoc;
import com.kkimjinoh.movieadmin.domain.community.docs.DeleteMessageDoc;
import com.kkimjinoh.movieadmin.domain.community.docs.GetMessageListDoc;
import com.kkimjinoh.movieadmin.domain.community.dto.request.RequestCreateMessageDto;
import com.kkimjinoh.movieadmin.domain.community.dto.response.ResponseGetMessageDto;
import com.kkimjinoh.movieadmin.domain.community.service.CommunityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 커뮤니티 게시글 Controller
 * 게시글 작성, 조회, 삭제 기능을 담당한다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
@Tag(name = "커뮤니티", description = "커뮤니티 기능")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping
    @CreateMessageDoc
    public ResponseEntity<ResponseGetMessageDto> createMessage(
            @Valid @RequestBody RequestCreateMessageDto body) {
        return ResponseEntity.ok(communityService.createMessage(body));
    }

    @GetMapping
    @GetMessageListDoc
    public ResponseEntity<List<ResponseGetMessageDto>> getMessageList() {
        return ResponseEntity.ok(communityService.getMessageList());
    }

    @DeleteMapping("{id}")
    @DeleteMessageDoc
    public ResponseEntity<StatusOkResponseDto> deleteMessage(@PathVariable("id") Long id) {
        return ResponseEntity.ok(communityService.deleteMessage(id));
    }
}
