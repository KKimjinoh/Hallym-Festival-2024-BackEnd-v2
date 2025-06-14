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
    COMMUNITY_MESSAGE_PASSWORD_MISMATCH (400, "COMMUNITY_MESSAGE_PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다"),

    // 분실물 에러
    LOST_ITEM_NOT_FOUND (400, "LOST_ITEM_NOT_FOUND", "해당 분실물을 찾을 수 없습니다"),

    // 공지사항 에러
    NOTICE_NOT_FOUND (400, "NOTICE_NOT_FOUND", "해당 공지사항을 찾을 수 없습니다"),

    // 사용자 그룹 에러
    USER_GROUP_NOT_FOUND (400, "USER_GROUP_NOT_FOUND", "해당 사용자 그룹을 찾을 수 없습니다"),

    // 사용자 에러
    USER_NOT_FOUND (400, "USER_NOT_FOUND", "해당 사용자를 찾을 수 없습니다"),
    INVALID_PASSWORD (400, "INVALID_PASSWORD", "비밀번호가 틀렸습니다"),
    INVALID_TOKEN (401, "INVALID_TOKEN", "유효하지 않은 토큰입니다"),
    ACCESS_DENIED (403, "ACCESS_DENIED", "접근 권한이 없습니다"),
    USER_ALREADY_INVITED (400, "USER_ALREADY_INVITED", "이미 초대된(등록된) 사용자입니다"),

    // AUTH 에러
    INVALID_AUTH_CODE (400, "INVALID_AUTH_CODE", "인증코드가 틀렸습니다"),
    AUTH_CODE_EXPIRED (400, "AUTH_CODE_EXPIRED", "인증코드가 만료되었습니다"),
    INVALID_SIGNUP_TOKEN (400, "INVALID_SIGNUP_TOKEN", "유효하지 않은 회원가입 토큰입니다"),
    SIGNUP_TOKEN_EXPIRED (400, "SIGNUP_TOKEN_EXPIRED", "회원가입 인증이 만료되었습니다");

    private final int status;
    private final String code;
    private final String message;
}
