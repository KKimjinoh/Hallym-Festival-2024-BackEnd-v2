package com.kkimjinoh.movieadmin.domain.lostitem.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.request.RequestUpdateLostItemDto;
import com.kkimjinoh.movieadmin.domain.lostitem.dto.response.ResponseGetLostItemDto;
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
        summary = "분실물 정보 수정",
        description = "특정 ID의 분실물 정보를 수정한다. (이미지 교체 및 회수 여부 포함)"
)
@Parameter(
        name = "id",
        required = true,
        description = "수정할 분실물 ID",
        example = "1"
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "multipart/form-data",
                schema = @Schema(implementation = RequestUpdateLostItemDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "수정된 분실물 정보 반환",
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
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "파일 업로드 실패",
                                        value = "{\n  \"status\": 500,\n  \"code\": \"FILE_UPLOAD_FAILED\",\n  \"message\": \"파일 업로드에 실패했습니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "존재하지 않는 분실물",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"LOST_ITEM_NOT_FOUND\",\n  \"message\": \"해당 분실물을 찾을 수 없습니다\"\n}"
                                ),
                        }
                )
        )
})
public @interface UpdateLostItemDoc {}
