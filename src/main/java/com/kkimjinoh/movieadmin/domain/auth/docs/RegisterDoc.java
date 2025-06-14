package com.kkimjinoh.movieadmin.domain.auth.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestRegisterDto;
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
        summary = "회원가입 정보 입력 및 최종 등록",
        description = "signupToken을 포함하여 회원가입 정보를 입력하고 회원 정보를 최종 등록합니다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestRegisterDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "회원가입 성공",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = StatusOkResponseDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 (회원가입 토큰 만료, 유효하지 않은 토큰, 사용자 미존재 등)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "회원가입 토큰 만료",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"SIGNUP_TOKEN_EXPIRED\",\n  \"message\": \"회원가입 인증이 만료되었습니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "유효하지 않은 회원가입 토큰",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"INVALID_SIGNUP_TOKEN\",\n  \"message\": \"유효하지 않은 회원가입 토큰입니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "존재하지 않는 사용자",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"USER_NOT_FOUND\",\n  \"message\": \"해당 사용자를 찾을 수 없습니다\"\n}"
                                )
                        }
                )
        )
})
public @interface RegisterDoc {}
