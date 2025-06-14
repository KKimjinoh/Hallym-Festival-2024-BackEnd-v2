package com.kkimjinoh.movieadmin.domain.auth.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestVerifyCodeDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.ResponseVerifyCodeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Documented
@SecurityRequirements
@Operation(
        summary = "인증코드 검증 및 signupToken 발급",
        description = "이메일과 인증코드를 검증한 후, 회원가입에 사용할 signupToken(JWT)을 발급합니다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestVerifyCodeDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "인증코드 검증 성공 (signupToken 포함)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseVerifyCodeDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 (인증코드 만료, 인증코드 오류, 사용자 미존재 등)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "인증코드 만료",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"AUTH_CODE_EXPIRED\",\n  \"message\": \"인증코드가 만료되었습니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "잘못된 인증코드",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"INVALID_AUTH_CODE\",\n  \"message\": \"인증코드가 틀렸습니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "존재하지 않는 사용자",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"USER_NOT_FOUND\",\n  \"message\": \"해당 사용자를 찾을 수 없습니다\"\n}"
                                )
                        }
                )
        )
})
public @interface VerifyCodeDoc {}
