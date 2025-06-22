package com.kkimjinoh.movieadmin.domain.community.service;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.global.util.PasswordUtil;
import com.kkimjinoh.movieadmin.domain.community.dto.request.RequestCreateMessageDto;
import com.kkimjinoh.movieadmin.domain.community.dto.response.ResponseGetMessageDto;
import com.kkimjinoh.movieadmin.domain.community.entity.CommunityEntity;
import com.kkimjinoh.movieadmin.domain.community.mapper.CommunityMapper;
import com.kkimjinoh.movieadmin.domain.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 커뮤니티 게시글 Service
 * 글 작성, 목록 조회, 삭제 로직을 담당한다.
 */
@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityMapper communityMapper;
    private final CommunityRepository communityRepository;

    /**
     * 새로운 커뮤니티 글을 생성하고, 비밀번호는 BCrypt 해싱하여 저장한다.
     *
     * @param body 요청 바디에 담긴 글 작성 정보
     * @return ResponseGetMessageDto 생성된 게시글의 상세 정보
     */
    @Transactional
    public ResponseGetMessageDto createMessage(RequestCreateMessageDto body) {
        body.setPassword(PasswordUtil.hash(body.getPassword()));
        CommunityEntity entity = communityMapper.ReqCreateMessageDtoToCommunityEntity(body);
        CommunityEntity saved = communityRepository.save(entity);
        return communityMapper.CommunityEntityToResGetMessageDto(saved);
    }

    /**
     * 모든 커뮤니티 글 목록을 조회한다.
     *
     * @return ResponseGetMessageListDto 존재하는 모든 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<ResponseGetMessageDto> getMessageList() {
        List<CommunityEntity> entities = communityRepository.findAll();
        return communityMapper.CommunityEntitiesToResGetMessageDtos(entities);
    }

    /**
     * 지정된 ID의 커뮤니티 글을 비밀번호 검증 후 삭제한다.
     *
     * @param id   삭제 대상 게시글의 고유 ID
     * @return StatusOkResponseDto 성공(OK) 응답
     */
    @Transactional
    public StatusOkResponseDto deleteMessage(Long id) {
        // 엔티티 조회, 없으면 예외
        CommunityEntity entity = communityRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.COMMUNITY_MESSAGE_NOT_FOUND));

        communityRepository.delete(entity);

        // OK 응답 반환
        return new StatusOkResponseDto();
    }
}
