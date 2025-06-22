package com.kkimjinoh.movieadmin.domain.reservation.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
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

/**
 * 예약 취소
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
@Operation(
        summary = "예약 취소",
        description = "예약 ID를 통해 예약을 취소합니다. 취소 시 대기자가 있을 경우 자동 승격됩니다."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "204",
                description = "예약 취소 성공 (내용 없음)"
        ),
        @ApiResponse(
                responseCode = "400",
                description = "존재하지 않는 예약 또는 이미 취소된 예약",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "예약 없음",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"RESERVATION_NOT_FOUND\",\n  \"message\": \"해당 예약을 찾을 수 없습니다\"\n}"
                                ),
                                @ExampleObject(
                                        name = "이미 취소됨",
                                        value = "{\n  \"status\": 400,\n  \"code\": \"RESERVATION_ALREADY_CANCELED\",\n  \"message\": \"이미 취소된 예약입니다\"\n}"
                                )
                        }
                )
        )
})
public @interface CancelReservationDoc {}