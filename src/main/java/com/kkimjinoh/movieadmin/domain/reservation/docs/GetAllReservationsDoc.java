package com.kkimjinoh.movieadmin.domain.reservation.docs;

import com.kkimjinoh.movieadmin.domain.reservation.dto.response.ResponseGetReservationDto;
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

/**
 * 예약 전체 조회
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
@Operation(
        summary = "예약 전체 조회",
        description = "예약된 사용자 및 대기자 목록을 조회합니다. 최대 200건까지 반환됩니다."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "예약 목록 반환",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseGetReservationDto.class)
                )
        )
})
public @interface GetAllReservationsDoc {}
