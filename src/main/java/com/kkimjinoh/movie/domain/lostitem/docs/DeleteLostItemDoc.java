package com.kkimjinoh.movie.domain.lostitem.docs;

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
        summary = "커뮤니티 글 삭제",
        description = "메시지 ID에 해당하는 커뮤니티 글을 비밀번호 검증을 후 삭제한다."
)
@Parameter(
        name = "id",
        required = true,
        description = "삭제할 글 ID",
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
                description = "존재하지 않는 분실물",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = @ExampleObject(
                                name = "분실물 없음",
                                value = "{\n  \"status\": 400,\n  \"code\": \"LOST_ITEM_NOT_FOUND\",\n  \"message\": \"해당 분실물을 찾을 수 없습니다\"\n}"
                        )
                )
        )
})
public @interface DeleteLostItemDoc {}
