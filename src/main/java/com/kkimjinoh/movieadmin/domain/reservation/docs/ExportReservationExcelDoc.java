package com.kkimjinoh.movieadmin.domain.reservation.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 예약 엑셀 추출
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
@Operation(
        summary = "예약자 엑셀 추출",
        description = "현재 예약/대기 정보를 엑셀 파일로 다운로드합니다."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "엑셀 파일 다운로드 (Content-Disposition: attachment)"
        )
})
public @interface ExportReservationExcelDoc {}
