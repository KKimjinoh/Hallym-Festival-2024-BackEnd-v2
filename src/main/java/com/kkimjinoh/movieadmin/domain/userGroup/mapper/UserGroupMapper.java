package com.kkimjinoh.movieadmin.domain.userGroup.mapper;

import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestCreateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestUpdateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.response.ResponseGetUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.entity.UserGroupEntity;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 사용자 그룹 Mapper
 */
@Mapper(componentModel = "spring")
public interface UserGroupMapper {

    /**
     * RequestCreateUserGroupDto → UserGroupEntity 변환
     */
    UserGroupEntity reqCreateDtoToUserGroupEntity(RequestCreateUserGroupDto requestCreateUserGroupDto);

    /**
     * RequestUpdateUserGroupDto → UserGroupEntity 변환
     */
    default UserGroupEntity reqUpdateDtoToUserGroupEntity(RequestUpdateUserGroupDto requestUpdateUserGroupDto, UserGroupEntity userGroupEntity) {
        return userGroupEntity.toBuilder()
                .name(requestUpdateUserGroupDto.getName())
                .build();
    }
    /**
     * UserGroupEntity → ResponseGetUserGroupDto 변환
     */
    ResponseGetUserGroupDto UserGroupEntityToResGetUserDto(UserGroupEntity userGroupEntity);

    /**
     * List<UserGroupEntity> → List<ResponseGetUserGroupDto> 변환
     */
    List<ResponseGetUserGroupDto> UserGroupEntitiesToResGetUserDtos(List<UserGroupEntity> userGroupEntities);

}
