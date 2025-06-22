package com.kkimjinoh.movieadmin.domain.reservation.docs;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.movieadmin.domain.reservation.dto.request.RequestReservationDto;
import com.kkimjinoh.movieadmin.domain.reservation.dto.response.ResponseGetReservationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 예약 신청
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
@Operation(
        summary = "선착순 예약 신청",
        description = "이름, 학번, 전화번호, 인원수 등을 기반으로 선착순 예약을 신청합니다."
)
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RequestReservationDto.class)
        )
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "예약 결과 반환 (성공/대기/중복/실패 등)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseGetReservationDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "잘못된 요청 (필수 값 누락 등)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
})
public @interface ReserveDoc {}