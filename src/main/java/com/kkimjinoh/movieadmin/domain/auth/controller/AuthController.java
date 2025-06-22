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
 * 로그인, 회원가입, 토큰 재발급, 이메일 인증 코드 검증 기능을 담당한다.
 */
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Tag(name = "사용자 인증", description = "인증 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @LoginDoc
    public ResponseEntity<ResponseLoginDto> login(@Valid @RequestBody RequestLoginDto body) {
        ResponseLoginDto response = authService.login(body);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/register")
    @RegisterDoc
    public ResponseEntity<StatusOkResponseDto> register(@Valid @RequestBody RequestRegisterDto body) {
        StatusOkResponseDto response = authService.register(body);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reissue")
    @ReissueDoc
    public ResponseEntity<ResponseReissueDto> reissue(@Valid @RequestBody RequestReissueDto body) {
        ResponseReissueDto response = authService.reissue(body.getAccessToken(), body.getRefreshToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-code")
    @VerifyCodeDoc
    public ResponseEntity<ResponseVerifyCodeDto> verifyCode(@Valid @RequestBody RequestVerifyCodeDto body) {
        ResponseVerifyCodeDto response = authService.verifyCode(body);
        return ResponseEntity.ok(response);
    }

}
