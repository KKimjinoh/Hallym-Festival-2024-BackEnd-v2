package com.kkimjinoh.movieadmin.domain.notice.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.notice.dto.request.RequestCreateNoticeDto;
import com.kkimjinoh.movieadmin.domain.notice.dto.response.ResponseGetNoticeDto;
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
        summary = "공지사항 등록",
        description = "새로운 공지사항을 등록한다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestCreateNoticeDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "등록된 공지사항 정보 반환",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseGetNoticeDto.class)
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
public @interface CreateNoticeDoc {}
