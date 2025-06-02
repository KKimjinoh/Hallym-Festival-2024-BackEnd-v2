package com.kkimjinoh.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 성공(OK) 응답을 위한 DTO 클래스.
 * 단순히 {"status":"ok"} 형태로 성공 여부를 표현한다.
 */
@Getter
@Schema(description = "성공(OK) 응답")
public class StatusOkResponseDto {

    @Schema(description = "성공", example = "ok")
    private final String status = "ok";
}
