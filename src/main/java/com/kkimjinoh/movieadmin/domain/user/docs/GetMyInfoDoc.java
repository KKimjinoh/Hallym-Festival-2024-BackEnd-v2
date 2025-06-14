package com.kkimjinoh.movieadmin.domain.user.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
        summary = "내 정보 조회",
        description = "현재 로그인된 사용자의 상세 정보를 조회한다."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "내 정보 반환",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseGetUserDetailDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "사용자 없음",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = @ExampleObject(
                                name = "USER_NOT_FOUND",
                                value = "{\n  \"status\": 400,\n  \"code\": \"USER_NOT_FOUND\",\n  \"message\": \"해당 사용자를 찾을 수 없습니다\"\n}"
                        )
                )
        )
})
public @interface GetMyInfoDoc {}