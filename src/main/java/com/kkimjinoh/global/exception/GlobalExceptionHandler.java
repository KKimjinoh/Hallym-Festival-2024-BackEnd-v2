package com.kkimjinoh.global.exception;

import com.kkimjinoh.global.dto.ErrorResponseDto;
import com.kkimjinoh.global.error.CommonError;
import com.kkimjinoh.global.error.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 전역(Global) 예외 처리를 담당하는 ControllerAdvice 클래스.
 * Spring MVC에서 발생하는 다양한 예외를 잡아서 ErrorResponseDto 형태로 반환한다.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Bean Validation(@Valid) 실패 시 발생하는 예외를 처리한다.
     * MethodArgumentNotValidException 또는 BindException 발생 시,
     * 에러 모델(CommonError.INVALID_INPUT)과 함께 필드별 오류 상세를 ErrorResponseDto에 담아 반환한다.
     *
     * @param ex MethodArgumentNotValidException 또는 BindException
     * @return 공통 에러(INVALID_INPUT)와 필드 오류 목록을 매핑한 ErrorResponseDto, HTTP 400
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ErrorResponseDto> handleInvalidInput(Exception ex) {
        log.warn("📌 검증 오류", ex);

        BindingResult bindingResult;
        if (ex instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
        } else {
            bindingResult = ((BindException) ex).getBindingResult();
        }

        // BindingResult에서 필드 오류 정보를 꺼내 DTO용 FieldError 리스트로 변환
        List<ErrorResponseDto.FieldError> fieldErrors = bindingResult.getFieldErrors().stream()
                .map(fe -> new ErrorResponseDto.FieldError(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity
                .status(CommonError.INVALID_INPUT.getStatus())
                .body(ErrorResponseDto.of(CommonError.INVALID_INPUT, fieldErrors));
    }

    /**
     * 도메인(비즈니스) 예외 발생 시 처리한다.
     * DomainException에 포함된 ErrorModel을 기반으로 ErrorResponseDto를 생성하여 반환한다.
     *
     * @param ex DomainException (ErrorModel 포함)
     * @return ErrorModel 기반 ErrorResponseDto, 해당 상태 코드
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponseDto> handleDomain(DomainException ex) {
        log.warn("📌 비즈니스 오류 {}", ex.getError().getCode(), ex);
        return build(ex.getError());
    }

    /**
     * 알 수 없는 예외(기타 Exception) 발생 시 처리한다.
     * INTERNAL_SERVER_ERROR 코드로 매핑하여 ErrorResponseDto를 반환한다.
     *
     * @param ex 예기치 않은 모든 예외
     * @return INTERNAL_SERVER_ERROR 기반 ErrorResponseDto, HTTP 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnknown(Exception ex) {
        log.error("❌ 예상치 못한 오류", ex);
        return build(CommonError.INTERNAL_SERVER_ERROR);
    }

    /**
     * 주어진 ErrorModel을 바탕으로 ErrorResponseDto를 생성하여 ResponseEntity로 반환한다.
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
