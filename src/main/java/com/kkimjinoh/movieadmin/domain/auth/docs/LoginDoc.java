package com.kkimjinoh.movieadmin.domain.auth.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestLoginDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.ResponseLoginDto;
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
        summary = "사용자 로그인",
        description = "이메일과 비밀번호로 로그인합니다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestLoginDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "로그인 성공 (Access/Refresh 토큰 및 사용자 정보 반환)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseLoginDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 (존재하지 않는 사용자, 비밀번호 불일치 등)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "존재하지 않는 사용자",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"USER_NOT_FOUND\",\n  \"message\": \"해당 사용자를 찾을 수 없습니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "비밀번호 불일치",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"INVALID_PASSWORD\",\n  \"message\": \"비밀번호가 틀렸습니다\"\n}"
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
public @interface LoginDoc {}