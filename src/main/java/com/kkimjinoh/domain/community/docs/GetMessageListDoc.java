package com.kkimjinoh.domain.community.docs;

import com.kkimjinoh.domain.community.dto.response.ResponseGetMessageListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Documented
@Operation(
        summary = "커뮤니티 글 목록 조회",
        description = "커뮤니티 글 목록을 조회한다."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "글 목록 조회 성공",
                content = @Content(schema = @Schema(implementation = ResponseGetMessageListDto.class))
        ),
})
public @interface GetMessageListDoc {}
