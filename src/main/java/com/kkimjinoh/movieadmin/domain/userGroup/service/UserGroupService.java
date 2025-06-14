package com.kkimjinoh.movieadmin.domain.userGroup.service;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestCreateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestUpdateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.response.ResponseGetUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.entity.UserGroupEntity;
import com.kkimjinoh.movieadmin.domain.userGroup.mapper.UserGroupMapper;
import com.kkimjinoh.movieadmin.domain.userGroup.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 사용자 그룹 Service
 * 사용자 그룹 생성, 조회, 수정, 삭제 로직을 담당한다.
 */
@Service
@RequiredArgsConstructor
public class UserGroupService {

    private final UserGroupMapper userGroupMapper;
    private final UserGroupRepository userGroupRepository;


    /**
     * 모든 사용자 그룹 목록을 조회한다.
     *
     * @return List<ResponseGetUserGroupDto> 존재하는 모든 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<ResponseGetUserGroupDto> getUserGroupList() {
        List<UserGroupEntity> entities = userGroupRepository.findAll();

        // 사용자 그룹이 없을 경우 예외 발생
        if (entities.isEmpty()) {
            throw new DomainException(DomainError.USER_GROUP_NOT_FOUND);
        }

        // List<UserGroupEntity> → List<ResponseGetUserGroupDto> 변환
        return userGroupMapper.UserGroupEntitiesToResGetUserDtos(entities);
    }

    /**
     * 새로운 사용자 그룹을 생성한다.
     *
     * @param body 요청 바디에 담긴 사용자 그룹 정보
     * @return ResponseGetUserGroupDto 생성된 사용자그룹의 상세 정보
     */
    @Transactional
    public ResponseGetUserGroupDto createUserGroup(RequestCreateUserGroupDto body) {

        // RequestCreateUserGroupDto → UserGroupEntity 변환
        UserGroupEntity entity = userGroupMapper.reqCreateDtoToUserGroupEntity(body);
        UserGroupEntity saved = userGroupRepository.save(entity);

        // UserGroupEntity → ResponseGetUserGroupDto 변환
        return userGroupMapper.UserGroupEntityToResGetUserDto(saved);
    }

    /**
     * 새로운 사용자 그룹을 수정한다.
     *
     * @param body 요청 바디에 담긴 사용자 그룹 정보
     * @return ResponseGetUserGroupDto 생성된 사용자그룹의 상세 정보
     */
    @Transactional
    public ResponseGetUserGroupDto updateUserGroup(Long id, RequestUpdateUserGroupDto body) {

        // 사용자 그룹 조회 (없으면 예외 발생)
        UserGroupEntity userGroup = userGroupRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.USER_GROUP_NOT_FOUND));

        // RequestUpdateUserGroupDto → UserGroupEntity 변환
        userGroup = userGroupMapper.reqUpdateDtoToUserGroupEntity(body, userGroup);
        userGroupRepository.save(userGroup);

        // UserGroupEntity → ResponseGetUserGroupDto 변환
        return userGroupMapper.UserGroupEntityToResGetUserDto(userGroup);
    }


    /**
     * 지정된 ID의 사용자 그룹을 삭제한다.
     *
     * @param id 삭제 대상 사용자 그룹 ID
     * @return StatusOkResponseDto 성공(OK) 응답
     */
    @Transactional
    public StatusOkResponseDto deleteUserGroup(Long id) {

        // 사용자 그룹 조회 (없으면 예외 발생)
        userGroupRepository.findById(id)
                .orElseThrow(() -> new DomainException(DomainError.USER_GROUP_NOT_FOUND));

        userGroupRepository.deleteById(id);

        // OK 응답 반환
        return new StatusOkResponseDto();
    }
}
