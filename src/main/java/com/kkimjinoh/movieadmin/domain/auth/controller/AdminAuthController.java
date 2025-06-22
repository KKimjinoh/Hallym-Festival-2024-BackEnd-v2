package com.kkimjinoh.movieadmin.domain.auth.controller;

import com.kkimjinoh.movieadmin.domain.auth.docs.*;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.*;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.*;
import com.kkimjinoh.movieadmin.domain.auth.service.AdminAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 인증 관련 API 컨트롤러 (Admin 전용)
 * 사용자 초대 기능을 담당한다.
 */
@RestController
@RequestMapping("/api/admin/auth")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "사용자 인증", description = "인증 API")
public class AdminAuthController {
    private final AdminAuthService AdminauthService;


    @PostMapping("/invite")
    @InviteDoc
    public ResponseEntity<ResponseInviteDto> invite(@Valid @RequestBody RequestInviteDto body) {
        return ResponseEntity.ok(AdminauthService.invite(body));
    }

}