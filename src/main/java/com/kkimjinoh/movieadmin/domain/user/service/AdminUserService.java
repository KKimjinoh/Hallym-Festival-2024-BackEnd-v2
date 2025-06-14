package com.kkimjinoh.movieadmin.domain.user.service;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.movieadmin.domain.user.dto.request.RequestUpdateUserDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDetailDto;
import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import com.kkimjinoh.movieadmin.domain.user.mapper.UserMapper;
import com.kkimjinoh.movieadmin.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 사용자 관련 비즈니스 로직을 처리하는 Service (Admin 전용)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    /**
     * 사용자들의 상세 정보를 조회한다.
     *
     * @return List<ResponseGetUserDetailDto> 사용자들의 상세 정보 응답 DTO
     */
    @Transactional(readOnly = true)
    public List<ResponseGetUserDetailDto> getUserDetailList() {
        List<UserEntity> users = userRepository.findAll();

        // 사용자 데이터가 존재하지 않으면 예외 발생
        if (users.isEmpty()) {
            throw new DomainException(DomainError.USER_NOT_FOUND);
        }

        // List<UserEntity> → List<ResponseGetUserDetailDto> 변환
        return userMapper.userEntitiesToResUserDetailDtos(users);
    }


    /**
     * 특정 사용자의 정보를 수정한다.
     *
     * @param id 사용자 ID
     * @param body 수정 요청 DTO
     * @return ResponseGetUserDetailDto 수정된 사용자 상세 응답 DTO
     */
    @Transactional
    public ResponseGetUserDetailDto updateUser(Long id, RequestUpdateUserDto body) {

        // 1. 사용자 존재 여부 확인
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // RequestUpdateUserDto → UserEntity 변환
        user = userMapper.reqUpdateUserDtoToUserEntity(body, user);
        userRepository.save(user);

        // UserEntity → ResponseGetUserDetailDto 변환
        return userMapper.userEntityToResGetUserDetailDto(user);
    }

    /**
     * 특정 사용자를 삭제한다.
     *
     * @param id 삭제할 사용자 ID
     * @return StatusOkResponseDto 수정된 사용자 상세 응답 DTO
     */
    @Transactional
    public StatusOkResponseDto deleteUser(Long id) {
        // 1. 사용자 존재 여부 확인
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // soft 삭제
        user.softDelete();

        // OK 응답 반환
        return new StatusOkResponseDto();
    }
}
