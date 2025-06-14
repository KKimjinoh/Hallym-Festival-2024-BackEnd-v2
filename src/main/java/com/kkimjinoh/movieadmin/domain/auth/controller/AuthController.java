package com.kkimjinoh.movieadmin.domain.auth.controller;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.auth.docs.*;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.*;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.*;
import com.kkimjinoh.movieadmin.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 인증 관련 API 컨트롤러
 */
@RestController
@RequestMapping("api/manager/auth")
@RequiredArgsConstructor
@Tag(name = "사용자 인증", description = "인증 API")
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인 요청
     */
    @PostMapping("/login")
    @LoginDoc
    public ResponseEntity<ResponseLoginDto> login(@Valid @RequestBody RequestLoginDto body) {
        ResponseLoginDto response = authService.login(body);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원가입 정보 입력 및 최종 등록
     */
    @PutMapping("/register")
    @RegisterDoc
    public ResponseEntity<StatusOkResponseDto> register(@Valid @RequestBody RequestRegisterDto body) {
        StatusOkResponseDto response = authService.register(body);
        return ResponseEntity.ok(response);
    }

    /**
     * 토큰 재발급 요청
     */
    @PostMapping("/reissue")
    @ReissueDoc
    public ResponseEntity<ResponseReissueDto> reissue(@Valid @RequestBody RequestReissueDto body) {
        ResponseReissueDto response = authService.reissue(body.getAccessToken(), body.getRefreshToken());
        return ResponseEntity.ok(response);
    }

    /**
     * 토큰 재발급 요청
     */
    @PostMapping("/verify-code")
    @VerifyCodeDoc
    public ResponseEntity<ResponseVerifyCodeDto> verifyCode(@Valid @RequestBody RequestVerifyCodeDto body) {
        ResponseVerifyCodeDto response = authService.verifyCode(body);
        return ResponseEntity.ok(response);
    }

}
