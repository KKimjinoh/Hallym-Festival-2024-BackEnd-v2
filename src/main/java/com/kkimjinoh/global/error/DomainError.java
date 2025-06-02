package com.kkimjinoh.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 도메인(비즈니스) 레벨에서 발생할 수 있는 구체적인 에러 상황을 정의한 열거형(Enum).
 * 서비스별로 확장하여 새로운 에러 코드를 추가할 수 있음.
 */
@Getter
@RequiredArgsConstructor
public enum DomainError implements ErrorModel {

    // 커뮤니티 에러
    COMMUNITY_MESSAGE_NOT_FOUND (400, "COMMUNITY_MESSAGE_NOT_FOUND", "해당 메시지를 찾을 수 없습니다"),
    COMMUNITY_MESSAGE_PASSWORD_MISMATCH (400, "COMMUNITY_MESSAGE_PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다");

    private final int status;
    private final String code;
    private final String message;
}
