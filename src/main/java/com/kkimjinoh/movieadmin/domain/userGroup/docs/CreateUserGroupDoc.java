package com.kkimjinoh.movieadmin.domain.userGroup.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestCreateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.response.ResponseGetUserGroupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Documented
@Operation(
        summary = "\uD83D\uDD11 사용자 그룹 생성",
        description = "Admin(관리자) 권한을 가진 사용자가 새로운 사용자 그룹 생성한다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestCreateUserGroupDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "생성된 사용자 그룹 정보 반환",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseGetUserGroupDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "잘못된 입력 값",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        ),
})
public @interface CreateUserGroupDoc {}
