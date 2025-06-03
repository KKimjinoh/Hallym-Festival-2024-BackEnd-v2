package com.kkimjinoh.movie.domain.community.service;

import com.kkimjinoh.movie.domain.community.dto.request.RequestCreateMessageDto;
import com.kkimjinoh.movie.domain.community.dto.request.RequestDeleteMessageDto;
import com.kkimjinoh.movie.domain.community.dto.response.ResponseGetMessageDto;
import com.kkimjinoh.movie.domain.community.dto.response.ResponseGetMessageListDto;
import com.kkimjinoh.movie.domain.community.entity.CommunityEntity;
import com.kkimjinoh.movie.domain.community.repository.CommunityRepository;
import com.kkimjinoh.global.util.PasswordUtil;
import org.springframework.transaction.annotation.Transactional;
import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 커뮤니티 게시글 Service
 * 글 작성, 목록 조회, 삭제 로직을 담당한다.
 */
@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    /**
     * 새로운 커뮤니티 글을 생성하고, 비밀번호는 BCrypt 해싱하여 저장한다.
     *
     * @param body 요청 바디에 담긴 글 작성 정보
     * @return ResponseGetMessageDto 생성된 게시글의 상세 정보
     */
    @Transactional
    public ResponseGetMessageDto createMessage(RequestCreateMessageDto body) {
        CommunityEntity entity = body.toEntity();
        // 비밀번호 해싱 후 저장
        entity.setPassword(PasswordUtil.hash(body.getPassword()));
        CommunityEntity saved = communityRepository.save(entity);
        return ResponseGetMessageDto.fromEntity(saved);
    }

    /**
     * 모든 커뮤니티 글 목록을 조회한다.
     *
     * @return ResponseGetMessageListDto 존재하는 모든 게시글 목록
     */
    @Transactional(readOnly = true)
    public ResponseGetMessageListDto getMessageList() {
        return ResponseGetMessageListDto.fromEntities(communityRepository.findAll());
    }

    /**
     * 지정된 ID의 커뮤니티 글을 비밀번호 검증 후 삭제한다.
     *
     * @param id   삭제 대상 게시글의 고유 ID
     * @param body 삭제용 비밀번호
     * @return StatusOkResponseDto 성공(OK) 응답
     */
    @Transactional
    public void deleteMessage(Long id, RequestDeleteMessageDto body) {
        // 엔티티 조회, 없으면 예외
        CommunityEntity entity = communityRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.COMMUNITY_MESSAGE_NOT_FOUND));

        // 비밀번호 비교 (불일치 시 예외)
        if (!PasswordUtil.matches(body.getPassword(), entity.getPassword())) {
            throw new DomainException(DomainError.COMMUNITY_MESSAGE_PASSWORD_MISMATCH);
        }
        communityRepository.deleteById(id);
    }
}
