package com.kkimjinoh.domain.community.docs;

import com.kkimjinoh.domain.community.dto.request.RequestCreateMessageDto;
import com.kkimjinoh.domain.community.dto.response.ResponseGetMessageDto;
import com.kkimjinoh.global.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Documented
@Operation(
        summary = "커뮤니티 글 작성",
        description = "새 커뮤니티 글을 작성한다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestCreateMessageDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "작성된 글 정보 반환",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseGetMessageDto.class)
                )
        ),

})
@ApiResponse(
        responseCode = "400",
        description = "잘못된 입력 값",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDto.class)
        )
)
public @interface CreateMessageDoc {}