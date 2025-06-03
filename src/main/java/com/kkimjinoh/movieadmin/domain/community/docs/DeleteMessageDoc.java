package com.kkimjinoh.movieadmin.domain.community.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.community.dto.request.RequestDeleteMessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        summary = "커뮤니티 글 삭제",
        description = "메시지 ID에 해당하는 커뮤니티 글을 비밀번호 검증을 후 삭제한다."
)
@Parameter(
        name = "id",
        required = true,
        description = "삭제할 글 ID",
        example = "1"
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestDeleteMessageDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "삭제 성공",
                content = @Content(schema = @Schema(implementation = StatusOkResponseDto.class))
        ),
        @ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 (존재하지 않거나 비밀번호 불일치 등)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "해당 게시글이 존재 하지 않을 때",
                                        value = "{\n" +
                                                "  \"status\": 400,\n" +
                                                "  \"code\": \"COMMUNITY_MESSAGE_NOT_FOUND\",\n" +
                                                "  \"message\": \"해당 메시지를 찾을 수 없습니다\"\n" +
                                                "}"
                                ),
                                @ExampleObject(
                                        name = "게시물 비밀번호가 올바르지 않을 때",
                                        value = "{\n" +
                                                "  \"status\": 400,\n" +
                                                "  \"code\": \"COMMUNITY_MESSAGE_PASSWORD_MISMATCH\",\n" +
                                                "  \"message\": \"비밀번호가 일치하지 않습니다\"\n" +
                                                "}"
                                ),
                                @ExampleObject(
                                        name = "INVALID_INPUT",
                                        value = "{\n" +
                                                "  \"status\": 400,\n" +
                                                "  \"code\": \"INVALID_INPUT\",\n" +
                                                "  \"message\": \"잘못된 입력 값\",\n" +
                                                "  \"errors\": [\n" +
                                                "    { \"field\": \"password\", \"message\": \"비밀번호는 비어 있을 수 없습니다.\" }\n" +
                                                "  ]\n" +
                                                "}"
                                )
                        }
                )
        ),
})
public @interface DeleteMessageDoc {}
