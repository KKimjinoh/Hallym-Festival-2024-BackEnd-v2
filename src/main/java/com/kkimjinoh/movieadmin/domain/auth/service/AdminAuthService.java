package com.kkimjinoh.movieadmin.domain.auth.service;

import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestInviteDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.*;
import com.kkimjinoh.movieadmin.domain.auth.entity.AuthCodeEntity;
import com.kkimjinoh.movieadmin.domain.auth.mapper.AuthMapper;
import com.kkimjinoh.movieadmin.domain.auth.repository.AuthCodeRepository;
import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import com.kkimjinoh.movieadmin.domain.user.repository.UserRepository;
import com.kkimjinoh.movieadmin.domain.userGroup.repository.UserGroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import static com.kkimjinoh.global.util.CodeGenerator.generateRandomCode;

/**
 * 관리자 인증 관련 서비스
 *
 * 회원 초대 등
 * 인증 및 회원 관련 로직을 담당한다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final AuthCodeRepository authCodeRepository;
    private final UserGroupRepository userGroupRepository;

    /**
     * 관리자가 새로운 사용자를 초대(등록)한다.
     *
     * @param body 초대 요청 DTO
     * @return ResponseInviteDto 생성된 사용자 정보 DTO
     */
    @Transactional
    public ResponseInviteDto invite(RequestInviteDto body) {

        // 소속 그룹 검증
        userGroupRepository.findById(body.getUserGroupId())
                .orElseThrow(() -> new DomainException(DomainError.USER_GROUP_NOT_FOUND));

        // 이미 해당 이메일이 등록(초대)된 사용자가 있는지 체크
        if (userRepository.findByEmail(body.getEmail()).isPresent()) {
            throw new DomainException(DomainError.USER_ALREADY_INVITED);
        }

        // 직접 Entity 생성 및 값 매핑
        UserEntity entity = authMapper.reqInviteDtoToUserEntity(body);
        UserEntity saved = userRepository.save(entity);

        // 현재 로그인 관리자 구하기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity admin = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // 인증코드(초대코드) 생성 및 저장
        String code = generateRandomCode(6);

        // (email + code + admin + expiredAt) → AuthCodeEntity 변환
        AuthCodeEntity authCode = authMapper.toAuthCodeEntity(saved.getEmail(), code, admin, LocalDateTime.now().plusDays(2));
        authCodeRepository.save(authCode);

        // 인증코드 로그 출력 (나중에 메일 발송으로 교체)
        log.info("[초대 코드 발급] {} ({})", code, saved.getEmail());

        // UserEntity → ResponseInviteDto 변환
        return authMapper.userEntityToResInviteDto(saved);
    }
}
