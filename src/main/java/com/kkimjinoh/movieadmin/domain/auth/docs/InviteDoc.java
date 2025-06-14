package com.kkimjinoh.movieadmin.domain.auth.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestInviteDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.ResponseInviteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
        summary = "\uD83D\uDD11 새로운 사용자 초대 및 등록 ",
        description = "Admin(관리자) 권한을 가진 사용자가 새로운 사용자를 초대 및 등록한다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestInviteDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "사용자 초대(등록) 정보 반환",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseInviteDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 (존재하지 않는 사용자 그룹, 이미 초대된 사용자, 존재하지 않는 관리자 등)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "존재하지 않는 사용자 그룹",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"USER_GROUP_NOT_FOUND\",\n  \"message\": \"해당 사용자 그룹을 찾을 수 없습니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "이미 초대된 사용자",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"USER_ALREADY_INVITED\",\n  \"message\": \"이미 초대된(등록된) 사용자입니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "존재하지 않는 관리자",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"USER_NOT_FOUND\",\n  \"message\": \"해당 사용자를 찾을 수 없습니다\"\n}"
                                )
                        }
                )
        ),
        @ApiResponse(
                responseCode = "403",
                description = "접근 권한이 없는 경우",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "접근 거부",
                                        value = "{\n  \"status\": 403,\n  \"code\": \"ACCESS_DENIED\",\n  \"message\": \"접근 권한이 없습니다\"\n}"
                                )
                        }
                )
        )

})
public @interface InviteDoc {}
