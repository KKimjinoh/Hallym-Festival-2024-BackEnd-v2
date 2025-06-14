package com.kkimjinoh.movieadmin.domain.auth.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestReissueDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.ResponseReissueDto;
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
        summary = "토큰 재발급",
        description = "기존 Access/Refresh 토큰으로 새로운 Access 토큰을 재발급합니다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestReissueDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "토큰 재발급 성공",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseReissueDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 (유효하지 않은 토큰, 사용자 미존재 등)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "유효하지 않은 토큰",
                                        value = "{\n  \"status\": 401,\n  \"code\": \"INVALID_TOKEN\",\n  \"message\": \"유효하지 않은 토큰입니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "존재하지 않는 사용자",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"USER_NOT_FOUND\",\n  \"message\": \"해당 사용자를 찾을 수 없습니다\"\n}"
                                )
                        }
                )
        )
})
public @interface ReissueDoc {}
