package com.kkimjinoh.movieadmin.domain.auth.mapper;

import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestInviteDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestRegisterDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.*;
import com.kkimjinoh.movieadmin.domain.auth.entity.AuthCodeEntity;
import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;


/**
 * 사용자 인증 관련 Mapper
 */
@Mapper(componentModel = "spring")
public interface AuthMapper {

    /**
     * RequestCreateUserDto → UserEntity 변환
     */
    @Mapping(source = "userGroupId", target = "userGroupEntity.id")
    UserEntity reqInviteDtoToUserEntity(RequestInviteDto requestInviteDto);

    /**
     * (email + code + admin + expiredAt) → AuthCodeEntity 변환
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdByUserId", source = "admin")
    AuthCodeEntity toAuthCodeEntity(String email, String code, UserEntity admin, LocalDateTime expiredAt);

    /**
     * UserEntity → ResponseInviteDto 변환
     */
    @Mapping(source = "userGroupEntity.id", target = "userGroupId")
    ResponseInviteDto userEntityToResInviteDto(UserEntity userEntity);

    /**
     * UserEntity + (Access 토큰, Refresh 토큰) → ResponseLoginDto 변환
     */
    ResponseLoginDto userEntityToResLoginDto(UserEntity user, String accessToken, String refreshToken);

    /**
     * Access 토큰 → ResponseReissueDto 변환
     */
    ResponseReissueDto toResReissueDto(String accessToken);

    /**
     * (email + signupToken) → ResponseVerifyCodeDto 변환
     */
    ResponseVerifyCodeDto toResponseVerifyCodeDto(String email, String signupToken);

    /**
     * UserEntity + RequestRegisterDto → UserEntity 변환 (toBuilder 방식)
     */
    default UserEntity ReqRegisterDtoToUserEntity(RequestRegisterDto requestRegisterDto,
                                                  UserEntity userEntity,
                                                  String encodedPassword) {
        return userEntity.toBuilder()
                .name(requestRegisterDto.getName())
                .password(encodedPassword)
                .studentNumber(requestRegisterDto.getStudentNumber())
                .phoneNumber(requestRegisterDto.getPhoneNumber())
                .privacyAgree(requestRegisterDto.isPrivacyAgree())
                .pushAgree(requestRegisterDto.isPushAgree())
                .build();
    }

}
