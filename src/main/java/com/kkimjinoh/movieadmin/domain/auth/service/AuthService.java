package com.kkimjinoh.movieadmin.domain.auth.service;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.global.error.DomainError;
import com.kkimjinoh.global.exception.DomainException;
import com.kkimjinoh.global.security.JwtTokenProvider;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestLoginDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestRegisterDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.request.RequestVerifyCodeDto;
import com.kkimjinoh.movieadmin.domain.auth.dto.response.*;
import com.kkimjinoh.movieadmin.domain.auth.entity.AuthCodeEntity;
import com.kkimjinoh.movieadmin.domain.auth.mapper.AuthMapper;
import com.kkimjinoh.movieadmin.domain.auth.repository.AuthCodeRepository;
import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import com.kkimjinoh.movieadmin.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * 사용자 인증 관련 서비스
 *
 * 회원 초대, 로그인, 토큰 재발급, 인증코드 검증, 회원가입 등
 * 인증 및 회원 관련 로직을 담당한다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthCodeRepository authCodeRepository;

    /**
     * 사용자 로그인 처리
     *
     * @param body 로그인 요청 DTO
     * @return ResponseLoginDto 로그인 응답 DTO
     */
    public ResponseLoginDto login(RequestLoginDto body) {

        // 이메일로 사용자 조회 (존재하지 않으면 예외 발생)
        UserEntity user = userRepository.findByEmail(body.getEmail())
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // 비밀번호 일치 여부 확인 (불일치 시 예외 발생)
        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            throw new DomainException(DomainError.INVALID_PASSWORD);
        }

        // Spring Security 인증 상태 확인 (미인증 시 예외 발생)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new DomainException(DomainError.ACCESS_DENIED);
        }

        // AccessToken, RefreshToken 생성
        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        // UserEntity + (Access 토큰, Refresh 토큰) → ResponseLoginDto 변환
        return authMapper.userEntityToResLoginDto(user,accessToken,refreshToken);
    }

    /**
     * Access 토큰 재발급 처리
     *
     * @param accessToken  기존 accessToken
     * @param refreshToken 기존 refreshToken
     * @return ResponseReissueDto access 토큰 재발급 응답 DTO
     */
    public ResponseReissueDto reissue(String accessToken, String refreshToken) {

        Claims accessClaims;
        try {
            // accessToken에서 Claims 추출 (만료된 토큰도 Claims는 파싱 가능)
            accessClaims = jwtTokenProvider.parseClaims(accessToken);
        } catch (ExpiredJwtException e) {
            // 만료된 경우 Claims만 추출
            accessClaims = e.getClaims();
        } catch (JwtException e) {
            // 변조/위조된 토큰은 예외 처리
            throw new DomainException(DomainError.INVALID_TOKEN);
        }

        // refreshToken 유효성 검사 (유효하지 않으면 예외)
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new DomainException(DomainError.INVALID_TOKEN);
        }

        // Access/RefreshToken에서 이메일(사용자) 정보 추출
        String emailFromAccess = accessClaims.getSubject();
        Claims refreshClaims = jwtTokenProvider.parseClaims(refreshToken);
        String emailFromRefresh = refreshClaims.getSubject();

        // 두 토큰의 유저 정보가 일치하지 않으면 예외 처리
        if (!emailFromAccess.equals(emailFromRefresh)) {
            throw new DomainException(DomainError.INVALID_TOKEN);
        }

        // 해당 이메일의 유저 조회 (없으면 예외)
        UserEntity user = userRepository.findByEmail(emailFromAccess)
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // 새로운 accessToken 생성
        String newAccessToken = jwtTokenProvider.createAccessToken(user);

        // 새로운 Access 토큰 → ResponseReissueDto 변환
        return authMapper.toResReissueDto(newAccessToken);
    }

    /**
     * 인증코드 검증(이메일/코드), soft delete 처리 및 signupToken 발급
     *
     * @param body 인증코드 검증 요청 DTO
     * @return ResponseVerifyCodeDto 인증코드 검증 응답 DTO (signupToken 포함)
     */
    @Transactional
    public ResponseVerifyCodeDto verifyCode(RequestVerifyCodeDto body) {

        // 이메일로 유저 조회 (없으면 예외)
        UserEntity user = userRepository.findByEmail(body.getEmail())
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // 인증코드로 코드 엔티티 조회 (없으면 예외)
        AuthCodeEntity code = authCodeRepository.findByCode(body.getCode())
                .orElseThrow(() -> new DomainException(DomainError.INVALID_AUTH_CODE));

        // 인증코드 만료 여부 확인 및 soft delete 처리 (만료 시 예외)
        if (code.getExpiredAt().isBefore(LocalDateTime.now())) {
            code.softDelete();
            authCodeRepository.save(code);
            throw new DomainException(DomainError.AUTH_CODE_EXPIRED);
        }

        // 인증코드 사용 처리 (soft delete)
        code.softDelete();
        authCodeRepository.save(code);

        // signupToken 발급
        String signupToken = jwtTokenProvider.createSignupToken(user);

        // (email + signupToken) → ResponseVerifyCodeDto 변환
        return authMapper.toResponseVerifyCodeDto(user.getEmail(),signupToken);
    }

    /**
     * 회원가입 정보 입력 및 최종 등록 (signupToken 검증)
     *
     * @param body 회원가입 요청 DTO
     * @return StatusOkResponseDto 성공(OK) 응답
     */
    @Transactional
    public StatusOkResponseDto register(RequestRegisterDto body) {

        Claims claims;
        try {
            // signupToken 파싱 및 검증
            claims = jwtTokenProvider.parseClaims(body.getSignupToken());
        } catch (ExpiredJwtException e) {
            // signupToken 만료 예외 처리
            throw new DomainException(DomainError.SIGNUP_TOKEN_EXPIRED);
        } catch (JwtException | IllegalArgumentException e) {
            // signupToken 변조/오류 예외 처리
            throw new DomainException(DomainError.INVALID_SIGNUP_TOKEN);
        }

        // 토큰 purpose 검증 (signup 목적만 허용)
        if (!"signup".equals(claims.get("purpose"))) {
            throw new DomainException(DomainError.INVALID_SIGNUP_TOKEN);
        }
        String email = claims.get("email", String.class);

        // 유저 조회 (없으면 예외)
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DomainException(DomainError.USER_NOT_FOUND));

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(body.getPassword());

        // UserEntity + RequestRegisterDto  → UserEntity 변환
        user = authMapper.ReqRegisterDtoToUserEntity(body, user, encodedPassword);
        userRepository.save(user);

        // OK 응답 반환
        return new StatusOkResponseDto();
    }

}
