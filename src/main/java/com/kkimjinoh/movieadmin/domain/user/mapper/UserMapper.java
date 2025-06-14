package com.kkimjinoh.movieadmin.domain.user.mapper;

import com.kkimjinoh.movieadmin.domain.user.dto.request.RequestUpdateMyInfoDto;
import com.kkimjinoh.movieadmin.domain.user.dto.request.RequestUpdateUserDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDetailDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDto;
import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import com.kkimjinoh.movieadmin.domain.userGroup.entity.UserGroupEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * 사용자 Mapper
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * UserEntity → ResponseGetUserDto 변환
     */
    @Mapping(source = "userGroupEntity.id", target = "userGroupId")
    ResponseGetUserDto userEntityToResponseGetUserDto(UserEntity userEntity);


    /**
     * List<UserEntity> → List<ResponseGetUserDto> 변환
     */
    List<ResponseGetUserDto> userEntitiesToResUserDtos(List<UserEntity> userEntities);

    /**
     * List<UserEntity> → List<ResponseGetUserDetailDto> 변환
     */
    List<ResponseGetUserDetailDto> userEntitiesToResUserDetailDtos(List<UserEntity> userEntities);

    /**
     * UserEntity → ResponseGetUserDetailDto 변환
     */
    @Mapping(source = "userGroupEntity.id", target = "userGroupId")
    ResponseGetUserDetailDto userEntityToResGetUserDetailDto(UserEntity userEntity);

    /**
     * RequestUpdateMyInfoDto → UserEntity 변환
     */
    default UserEntity reqUpdateMyInfoDtoToUserEntity(RequestUpdateMyInfoDto requestUpdateMyInfoDto, UserEntity user) {
        return user.toBuilder()
                .userGroupEntity(UserGroupEntity.builder()
                        .id(requestUpdateMyInfoDto.getUserGroupId())
                        .build())
                .name(requestUpdateMyInfoDto.getName())
                .phoneNumber(requestUpdateMyInfoDto.getPhoneNumber())
                .studentNumber(requestUpdateMyInfoDto.getStudentNumber())
                .privacyAgree(requestUpdateMyInfoDto.isPrivacyAgree())
                .pushAgree(requestUpdateMyInfoDto.isPushAgree())
                .build();
    }

    /**
     * RequestUpdateUserDto → UserEntity 변환
     */
    default UserEntity reqUpdateUserDtoToUserEntity(RequestUpdateUserDto requestUpdateUserDto, UserEntity user) {
        return user.toBuilder()
                .userRole(requestUpdateUserDto.getUserRole())
                .userGroupEntity(UserGroupEntity.builder()
                        .id(requestUpdateUserDto.getUserGroupId())
                        .build())
                .name(requestUpdateUserDto.getName())
                .phoneNumber(requestUpdateUserDto.getPhoneNumber())
                .studentNumber(requestUpdateUserDto.getStudentNumber())
                .privacyAgree(requestUpdateUserDto.isPrivacyAgree())
                .pushAgree(requestUpdateUserDto.isPushAgree())
                .build();
    }
}
