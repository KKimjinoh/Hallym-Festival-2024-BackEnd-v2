package com.kkimjinoh.movie.domain.notice.docs;

import com.kkimjinoh.movie.domain.notice.dto.response.ResponseGetNoticesListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
        summary = "공지사항 목록 조회",
        description = "등록된 모든 공지사항 목록을 조회한다."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "공지사항 목록 조회 성공",
                content = @Content(schema = @Schema(implementation = ResponseGetNoticesListDto.class))
        )
})
public @interface GetNoticesListDoc {}
