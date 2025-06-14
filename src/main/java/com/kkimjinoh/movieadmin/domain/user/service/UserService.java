package com.kkimjinoh.movieadmin.domain.user.service;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.movieadmin.domain.user.dto.request.RequestUpdateMyInfoDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDetailDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDto;
import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import com.kkimjinoh.movieadmin.domain.user.mapper.UserMapper;
import com.kkimjinoh.movieadmin.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 사용자 관련 비즈니스 로직을 처리하는 Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    /**
     * 사용자들의 정보를 조회한다.
     *
     * @return List<ResponseGetUserDto> 사용자들의 정보 응답 DTO
     */
    @Transactional(readOnly = true)
    public List<ResponseGetUserDto> getUserList() {
        List<UserEntity> users = userRepository.findAll();

        // 사용자 데이터가 존재하지 않으면 예외 발생
        if (users.isEmpty()) {
            throw new DomainException(DomainError.USER_NOT_FOUND);
        }

        // List<UserEntity> → List<ResponseGetUserDto> 변환
        return userMapper.userEntitiesToResUserDtos(users);
    }


    /**
     * 현재 로그인한 사용자의 정보를 조회한다.
     *
     * @return ResponseGetUserDetailDto 로그인된 사용자 정보 응답 DTO
     */
    @Transactional(readOnly = true)
    public ResponseGetUserDetailDto getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 이메일로 사용자 조회, 없으면 예외
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // UserEntity → ResponseGetUserDetailDto 변환
        return userMapper.userEntityToResGetUserDetailDto(user);
    }

    /**
     * 현재 로그인한 사용자의 정보를 수정한다.
     *
     * @param body 사용자 정보 수정 요청 DTO
     * @return ResponseGetUserDetailDto 수정된 사용자 정보 응답 DTO
     */
    @Transactional
    public ResponseGetUserDetailDto updateMyInfo(RequestUpdateMyInfoDto body) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 이메일로 사용자 조회, 없으면 예외
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // RequestUpdateMyInfoDto → UserEntity 변환
        user = userMapper.reqUpdateMyInfoDtoToUserEntity(body, user);
        userRepository.save(user);

        // UserEntity → ResponseGetUserDetailDto 변환
        return userMapper.userEntityToResGetUserDetailDto(user);
    }

    /**
     * 현재 로그인한 사용자를 탈퇴 처리한다 (Soft Delete).
     */
    @Transactional
    public StatusOkResponseDto deleteMyAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 이메일로 사용자 조회, 없으면 예외
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // soft 삭제
        user.softDelete();

        // OK 응답 반환
        return new StatusOkResponseDto();
    }
}
