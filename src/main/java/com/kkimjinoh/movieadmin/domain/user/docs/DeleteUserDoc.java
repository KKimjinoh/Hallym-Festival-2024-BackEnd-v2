package com.kkimjinoh.movieadmin.domain.user.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.global.dto.StatusOkResponseDto;
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
        summary = "\uD83D\uDD11 특정 사용자 삭제",
        description = "관리자가 사용자를 삭제한다."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "삭제 성공",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = StatusOkResponseDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "존재하지 않는 사용자",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = @ExampleObject(
                                name = "사용자 없음",
                                value = "{\n  \"status\": 400,\n  \"code\": \"USER_NOT_FOUND\",\n  \"message\": \"사용자를 찾을 수 없습니다\"\n}"
                        )
                )
        ),
        @ApiResponse(
                responseCode = "403",
                description = "접근 권한 없음",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = @ExampleObject(
                                name = "접근 거부",
                                value = "{\n  \"status\": 403,\n  \"code\": \"ACCESS_DENIED\",\n  \"message\": \"접근 권한이 없습니다\"\n}"
                        )
                )
        )
})
public @interface DeleteUserDoc {}
