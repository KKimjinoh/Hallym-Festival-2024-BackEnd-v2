package com.kkimjinoh.movieadmin.domain.userGroup.docs;

import com.kkimjinoh.movieadmin.domain.userGroup.dto.response.ResponseGetUserGroupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
        summary = "사용자 그룹 목록 조회",
        description = "등록된 모든 사용자 그룹 목록을 조회한다."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "사용자 그룹 목록 조회 성공",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseGetUserGroupDto.class)))
        )
})
public @interface GetUserGroupListDoc {}
