package com.kkimjinoh.movieadmin.domain.userGroup.mapper;

import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestCreateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestUpdateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.response.ResponseGetUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.entity.UserGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserGroupMapper {

    /**
     * RequestCreateUserGroupDto → UserGroupEntity 변환
     */
    UserGroupEntity requestCreateDtoToEntity(RequestCreateUserGroupDto requestCreateUserGroupDto);

    /**
     * RequestUpdateUserGroupDto → 기존 UserGroupEntity 병합
     */
    void updateEntityFromRequestUpdateDto(RequestUpdateUserGroupDto requestUpdateUserGroupDto, @MappingTarget UserGroupEntity entity);

    /**
     * UserGroupEntity → ResponseGetUserGroupDto 변환
     */
    ResponseGetUserGroupDto entityToResponseDto(UserGroupEntity userGroupEntity);

    /**
     * List<UserGroupEntity> → List<ResponseGetUserGroupDto> 변환
     */
    List<ResponseGetUserGroupDto> entityToResponseDtoList(List<UserGroupEntity> userGroupEntities);

}
