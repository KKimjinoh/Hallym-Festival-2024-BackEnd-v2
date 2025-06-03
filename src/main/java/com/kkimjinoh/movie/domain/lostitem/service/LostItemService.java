package com.kkimjinoh.movie.domain.lostitem.service;

import com.kkimjinoh.movie.domain.lostitem.dto.response.ResponseGetLostItemsListDto;
import com.kkimjinoh.movie.domain.lostitem.repository.LostItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 분실물 Service
 * 분실물 조회 로직을 담당한다.
 */
@Service
@RequiredArgsConstructor
public class LostItemService {

    private final LostItemRepository lostItemRepository;

    /**
     * 전체 분실물들을 조회한다.
     *
     * @return  ResponseGetLostItemsListDto 존재하는 모든 분실물 목록
     */
    @Transactional(readOnly = true)
    public ResponseGetLostItemsListDto getMessageList() {
        return ResponseGetLostItemsListDto.fromEntities(lostItemRepository.findAll());
    }
}
