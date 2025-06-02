package com.kkimjinoh.global.dto;

import com.kkimjinoh.global.error.ErrorModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 에러 응답을 위한 DTO 클래스.
 * HTTP 상태 코드, 에러 코드, 메시지를 JSON 형태로 포함한다.
 */
@Getter
@Builder
@AllArgsConstructor
@Schema(description = "오류 응답")
public class ErrorResponseDto {

    @Schema(description = "HTTP 상태 코드", example = "400")
    private final int status;

    @Schema(description = "에러 코드", example = "COMMUNITY_MESSAGE_NOT_FOUND")
    private final String code;

    @Schema(description = "에러 상세 메시지", example = "해당 메시지를 찾을 수 없습니다")
    private final String message;

    public static ErrorResponseDto of(ErrorModel e) {
        return new ErrorResponseDto(e.getStatus(), e.getCode(), e.getMessage());
    }
}
