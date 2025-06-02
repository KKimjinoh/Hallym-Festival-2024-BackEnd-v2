package com.kkimjinoh.global.exception;

import com.kkimjinoh.global.error.ErrorModel;
import lombok.Getter;

/**
 * 도메인(비즈니스) 예외를 나타내는 런타임 예외 클래스.
 * ErrorModel을 통해 HTTP 상태 코드, 에러 코드, 메시지를 함께 저장한다.
 */
@Getter
public class DomainException extends RuntimeException {

    private final ErrorModel error;

    /**
     * 주어진 ErrorModel을 사용하여 DomainException을 생성한다.
     *
     * @param error 발생한 도메인 예외 정보(상태 코드, 에러 코드, 메시지)를 포함하는 ErrorModel
     */
    public DomainException(ErrorModel error) {
        super(error.getMessage());
        this.error = error;
    }
}
