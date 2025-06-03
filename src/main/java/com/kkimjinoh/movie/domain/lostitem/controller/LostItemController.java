package com.kkimjinoh.movie.domain.lostitem.controller;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movie.domain.lostitem.docs.CreateLostItemDoc;
import com.kkimjinoh.movie.domain.lostitem.docs.DeleteLostItemDoc;
import com.kkimjinoh.movie.domain.lostitem.docs.GetLostItemsListDoc;
import com.kkimjinoh.movie.domain.lostitem.docs.UpdateLostItemDoc;
import com.kkimjinoh.movie.domain.lostitem.dto.request.RequestCreateLostItemDto;
import com.kkimjinoh.movie.domain.lostitem.dto.request.RequestUpdateLostItemDto;
import com.kkimjinoh.movie.domain.lostitem.dto.response.ResponseGetLostItemDto;
import com.kkimjinoh.movie.domain.lostitem.dto.response.ResponseGetLostItemsListDto;
import com.kkimjinoh.movie.domain.lostitem.service.LostItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 분실물 Controller
 * 분실물 조회 기능을 담당한다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lostitem")
@Tag(name = "분실물", description = "분실물 조회 기능")
public class LostItemController {

    private final LostItemService lostItemService;

    @GetMapping
    @GetLostItemsListDoc
    public ResponseEntity<ResponseGetLostItemsListDto> getMessageList() {
        return ResponseEntity.ok(lostItemService.getMessageList());
    }
}
