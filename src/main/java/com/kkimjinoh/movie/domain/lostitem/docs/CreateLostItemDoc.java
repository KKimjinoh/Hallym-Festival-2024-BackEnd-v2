package com.kkimjinoh.movie.domain.lostitem.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movie.domain.lostitem.dto.request.RequestCreateLostItemDto;
import com.kkimjinoh.movie.domain.lostitem.dto.response.ResponseGetLostItemDto;
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
        summary = "분실물 등록",
        description = "새로운 분실물을 등록한다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "multipart/form-data",
                schema = @Schema(implementation = RequestCreateLostItemDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "등록된 분실물 정보 반환",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseGetLostItemDto.class)
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
        @ApiResponse(
                responseCode = "500",
                description = "파일 업로드 실패",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = @ExampleObject(
                                name = "파일 업로드 실패",
                                value = "{\n  \"status\": 500,\n  \"code\": \"FILE_UPLOAD_FAILED\",\n  \"message\": \"파일 업로드에 실패했습니다\"\n}"
                        )
                )
        )
})
public @interface CreateLostItemDoc {}
