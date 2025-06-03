package com.kkimjinoh.movieadmin.domain.lostitem.controller;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.lostitem.docs.CreateLostItemDoc;
import com.kkimjinoh.movieadmin.domain.lostitem.docs.DeleteLostItemDoc;
import com.kkimjinoh.movieadmin.domain.lostitem.docs.GetLostItemsListDoc;
import com.kkimjinoh.movieadmin.domain.lostitem.docs.UpdateLostItemDoc;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.request.RequestCreateLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.request.RequestUpdateLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.response.ResponseGetLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.response.ResponseGetLostItemsListDto;
import com.kkimjinoh.movieadmin.domain.lostitem.service.LostItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 분실물 Controller
 * 분실물 조회, 추가, 수정, 삭제 기능을 담당한다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/lostitem")
@Tag(name = "분실물", description = "분실물 관리 기능")
public class LostItemController {

    private final LostItemService lostItemService;

    @PostMapping
    @CreateLostItemDoc
    public ResponseEntity<ResponseGetLostItemDto> createLostItem(
            @Valid @ModelAttribute RequestCreateLostItemDto body) {
        return ResponseEntity.ok(lostItemService.createLostItem(body));
    }

    @GetMapping
    @GetLostItemsListDoc
    public ResponseEntity<ResponseGetLostItemsListDto> getMessageList() {
        return ResponseEntity.ok(lostItemService.getMessageList());
    }

    @PutMapping("/{id}")
    @UpdateLostItemDoc
    public ResponseEntity<ResponseGetLostItemDto> updateLostItem(
            @PathVariable("id") Long id,
            @ModelAttribute RequestUpdateLostItemDto body) {
        return ResponseEntity.ok(lostItemService.updateLostItem(id, body));
    }

    @DeleteMapping("/{id}")
    @DeleteLostItemDoc
    public ResponseEntity<StatusOkResponseDto> deleteMessage(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(lostItemService.deleteMessage(id));
    }
}
