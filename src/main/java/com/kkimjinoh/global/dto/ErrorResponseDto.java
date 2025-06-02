package com.kkimjinoh.global.dto;

import com.kkimjinoh.global.error.ErrorModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

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

    @Schema(description = "에러 코드", example = "INVALID_INPUT")
    private final String code;

    @Schema(description = "에러 상세 메시지", example = "잘못된 입력 값")
    private final String message;

    @Schema(description = "필드 검증 오류 상세 목록 (옵션)")

    private final List<FieldError> errors;

    public static ErrorResponseDto of(ErrorModel e) {
        return new ErrorResponseDto(e.getStatus(), e.getCode(), e.getMessage(), null);
    }

    /** MethodArgumentNotValidException용 생성자 */
    public static ErrorResponseDto of(ErrorModel e, List<FieldError> fieldErrors) {
        return new ErrorResponseDto(e.getStatus(), e.getCode(), e.getMessage(), fieldErrors);
    }

    @Getter @AllArgsConstructor
    @Schema(description = "검증 오류가 발생한 필드별 상세 정보")
    public static class FieldError {
        @Schema(description = "잘못된 필드", example = "(필드명)")
        private String field;

        @Schema(description = "발생한 에러 메시지", example = "(필드)은/는 비어 있을 수 없습니다.")
        private String message;
    }
}