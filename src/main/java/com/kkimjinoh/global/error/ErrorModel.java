package com.kkimjinoh.global.error;

/**
 * 에러 정보(상태 코드, 에러 코드, 메시지)를 표현하기 위한 인터페이스.
 */
public interface ErrorModel {
    int getStatus();
    String getCode();
    String getMessage();
}