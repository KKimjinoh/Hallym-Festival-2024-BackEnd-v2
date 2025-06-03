package com.kkimjinoh.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 공통으로 발생할 수 있는 에러 상황을 정의한 열거형(Enum).
 * HTTP 상태 코드, 에러 코드 문자열, 사용자에게 보여줄 메시지를 함께 명시한다.
 */
@Getter
@RequiredArgsConstructor
public enum CommonError implements ErrorModel {

    // 인증 관련 에러
    UNAUTHORIZED            (401, "UNAUTHORIZED",             "로그인이 필요합니다"),
    ACCESS_DENIED           (403, "ACCESS_DENIED",            "권한이 없습니다"),

    // 공통 에러
    INVALID_INPUT           (400, "INVALID_INPUT",            "잘못된 입력 값"),
    METHOD_NOT_ALLOWED      (405, "METHOD_NOT_ALLOWED",       "지원하지 않는 HTTP Method"),
    INTERNAL_SERVER_ERROR   (500, "INTERNAL_SERVER_ERROR",    "서버 내부 오류"),

    // 파일 관련 에러
    FILE_UPLOAD_FAILED      (500, "FILE_UPLOAD_FAILED",    "파일 업로드에 실패했습니다.");

    private final int status;
    private final String code;
    private final String message;
}