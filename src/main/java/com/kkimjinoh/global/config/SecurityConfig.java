package com.kkimjinoh.global.config;

import com.kkimjinoh.global.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .sessionManagement(session -> session.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. 일부 엔드포인트는 허용
                        .requestMatchers(
                                "/api/admin/auth/login",
                                "/api/admin/auth/verify-code",
                                "/api/admin/auth/reissue",
                                "/auth/register",
                                "/auth/verify",
                                "/swagger-ui/**",
                                "/api-docs/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // 2. 관리자 API는 인증 필요
                        .requestMatchers("/api/admin/**").authenticated()

                        // 3. 그 외 일반 API는 모두 허용
                        .requestMatchers("/api/**").permitAll()

                        // 4. 그 외 나머지는 인증 필요
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 권한 계층 구조 설정 추가
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        // 역할 계층 설정: ADMIN > MANAGER > OPERATOR
        roleHierarchy.setHierarchy(
                "ROLE_ADMIN > ROLE_MANAGER \nROLE_MANAGER > ROLE_OPERATOR"
        );
        return roleHierarchy;
    }

}
