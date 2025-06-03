package com.kkimjinoh.movieadmin.domain.notice.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.global.dto.StatusOkResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        summary = "공지사항 삭제",
        description = "특정 ID의 공지사항을 삭제한다."
)
@Parameter(
        name = "id",
        required = true,
        description = "삭제할 공지사항 ID",
        example = "1"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "삭제 성공",
                content = @Content(schema = @Schema(implementation = StatusOkResponseDto.class))
        ),
        @ApiResponse(
                responseCode = "400",
                description = "존재하지 않는 공지사항",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "존재하지 않는 공지사항",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"NOTICE_NOT_FOUND\",\n  \"message\": \"해당 공지사항을 찾을 수 없습니다\"\n}"
                                ),
                        }
                )
        )
})
public @interface DeleteNoticeDoc {}
