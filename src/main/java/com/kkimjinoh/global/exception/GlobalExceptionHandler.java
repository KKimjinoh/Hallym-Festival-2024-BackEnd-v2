package com.kkimjinoh.global.exception;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.global.error.CommonError;
import com.kkimjinoh.global.error.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역(Global) 예외 처리를 담당하는 ControllerAdvice 클래스.
 * Spring MVC에서 발생하는 다양한 예외를 잡아서 ErrorResponseDto 형태로 반환한다.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Bean Validation(@Valid) 실패 시 발생하는 예외를 처리한다.
     *
     * @param ex MethodArgumentNotValidException 또는 BindException
     * @return 공통 에러(INVALID_INPUT)로 매핑된 ErrorResponseDto와 상태 코드 400
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ErrorResponseDto> handleInvalidInput(Exception ex) {
        log.warn("📌 검증 오류", ex);
        return build(CommonError.INVALID_INPUT);
    }

    /**
     * 도메인(비즈니스) 예외 발생 시 처리한다.
     *
     * @param ex DomainException (ErrorModel 포함)
     * @return 예외에 포함된 ErrorModel을 기반으로 한 ErrorResponseDto와 해당 상태 코드
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponseDto> handleDomain(DomainException ex) {
        log.warn("📌 비즈니스 오류 {}" , ex.getError().getCode(), ex);
        return build(ex.getError());
    }

    /**
     * 알 수 없는 예외(기타 Exception) 발생 시 처리한다.
     *
     * @param ex 예기치 않은 모든 예외
     * @return INTERNAL_SERVER_ERROR 코드에 매핑된 ErrorResponseDto와 상태 코드 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnknown(Exception ex) {
        log.error("❌ 예상치 못한 오류", ex);
        return build(CommonError.INTERNAL_SERVER_ERROR);
    }

    /**
     * 주어진 ErrorModel을 바탕으로 공통 ErrorResponseDto를 생성하여 ResponseEntity로 반환합니다.
     *
     * @param em 에러 코드, 상태 코드, 메시지를 포함하는 ErrorModel
     * @return ResponseEntity에 ErrorResponseDto(body)와 HTTP 상태 정보를 담아 반환
     */
    private ResponseEntity<ErrorResponseDto> build(ErrorModel em) {
        return ResponseEntity
                .status(em.getStatus())
                .body(ErrorResponseDto.of(em));
    }
}
