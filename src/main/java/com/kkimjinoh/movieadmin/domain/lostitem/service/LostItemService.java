package com.kkimjinoh.movieadmin.domain.lostitem.service;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.global.error.CommonError;
import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.global.util.S3Uploader;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.request.RequestCreateLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.request.RequestUpdateLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.response.ResponseGetLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.entity.LostItemEntity;
import com.kkimjinoh.movieadmin.domain.lostitem.mapper.LostItemMapper;
import com.kkimjinoh.movieadmin.domain.lostitem.repository.LostItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * 분실물 Service
 * 분실물 추가, 조회, 수정, 삭제 로직을 담당한다.
 */
@Service
@RequiredArgsConstructor
public class LostItemService {

    private final S3Uploader s3Uploader;
    private final LostItemMapper lostItemMapper;
    private final LostItemRepository lostItemRepository;

    /**
     * 새로운 분실물을 추가하고, 이미지를 S3에 업로드한다.
     *
     * @param body 요청 바디에 담긴 글 작성 정보
     * @return ResponseGetMessageDto 생성된 게시글의 상세 정보
     */
    @Transactional
    public ResponseGetLostItemDto createLostItem(RequestCreateLostItemDto body) {
        String imageUrl;
        try {
            imageUrl = s3Uploader.uploadFile(body.getLostItemImage(), "lost-items");
        } catch (IOException e) {
            throw new DomainException(CommonError.FILE_UPLOAD_FAILED);
        }

        LostItemEntity entity = lostItemMapper.reqCreateDtoToLostItemEntity(body, imageUrl);
        LostItemEntity saved = lostItemRepository.save(entity);
        return lostItemMapper.lostItemEntityToResDto(saved);
    }


    /**
     * 분실물을 수정하고, 이미지가 새로 올라온 경우 기존 이미지를 삭제하고 교체한다.
     */
    @Transactional
    public ResponseGetLostItemDto updateLostItem(Long id, RequestUpdateLostItemDto body) {
        LostItemEntity entity = lostItemRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.LOST_ITEM_NOT_FOUND));

        String imageUrl = entity.getImageUrl();

        if (body.getLostItemImage() != null && !body.getLostItemImage().isEmpty()) {
            try {
                imageUrl = s3Uploader.uploadFile(body.getLostItemImage(), "lost-items");
                s3Uploader.deleteFile(entity.getImageUrl());
            } catch (IOException e) {
                throw new DomainException(CommonError.FILE_UPLOAD_FAILED);
            }
        }

        LostItemEntity updated = lostItemMapper.reqUpdateDtoToLostItemEntity(body, entity, imageUrl);
        LostItemEntity saved = lostItemRepository.save(updated);
        return lostItemMapper.lostItemEntityToResDto(saved);
    }


    /**
     * 전체 분실물들을 조회한다.
     *
     * @return List<ResponseGetLostItemDto> 전체 분실물 목록
     */
    @Transactional(readOnly = true)
    public List<ResponseGetLostItemDto> getMessageList() {
        return lostItemMapper.lostItemEntitiesToResDtos(lostItemRepository.findAll());
    }

    /**
     * 지정된 ID의 분실물을 삭제한다.
     *
     * @param id 삭제 대상 분실물의 ID
     * @return StatusOkResponseDto 성공(OK) 응답
     */
    @Transactional
    public StatusOkResponseDto deleteMessage(Long id) {
        LostItemEntity entity = lostItemRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.LOST_ITEM_NOT_FOUND));
        // S3 이미지 삭제
        s3Uploader.deleteFile(entity.getImageUrl());
        lostItemRepository.deleteById(id);
        return new StatusOkResponseDto();
    }
}
