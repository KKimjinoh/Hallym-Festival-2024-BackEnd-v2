package com.kkimjinoh.global.security;

import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;
    private final long signupTokenExpiration;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration,
            @Value("${jwt.signup-token-expiration}") long signupTokenExpiration
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.signupTokenExpiration = signupTokenExpiration;
    }

    /** Access Token 발급 */
    public String createAccessToken(UserEntity user) {
        return createUserToken(user, accessTokenExpiration, "access");
    }

    /** Refresh Token 발급 */
    public String createRefreshToken(UserEntity user) {
        return createUserToken(user, refreshTokenExpiration, "refresh");
    }

    /** 회원가입용 SignupToken 발급 */
    public String createSignupToken(UserEntity user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("purpose", "signup");
        claims.put("email", user.getEmail());
        Date now = new Date();
        Date expiry = new Date(now.getTime() + signupTokenExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** 사용자 토큰 생성 */
    private String createUserToken(UserEntity user, long expiration, String purpose) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("userId", user.getId());
        claims.put("role", user.getUserRole().name());
        claims.put("purpose", purpose);
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** 인증 객체 생성 */
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        String email = claims.getSubject();
        String role = (String) claims.get("role");

        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                role != null ? Collections.singletonList(new SimpleGrantedAuthority(role)) : Collections.emptyList()
        );
    }

    /** Authorization 헤더에서 토큰 추출 */
    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    /** JWT 유효성 검증 */
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("JWT expired: {}", e.getMessage());
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Invalid JWT: {}", e.getMessage());
        }
        return false;
    }

    /** JWT Claims 파싱 */
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
