package com.kkimjinoh.movieadmin.domain.user.initializer;

import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import com.kkimjinoh.movieadmin.domain.user.entity.UserRole;
import com.kkimjinoh.movieadmin.domain.user.repository.UserRepository;
import com.kkimjinoh.movieadmin.domain.userGroup.entity.UserGroupEntity;
import com.kkimjinoh.movieadmin.domain.userGroup.repository.UserGroupRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment env;

    @PostConstruct
    public void init() {

        if (userRepository.existsByUserRole(UserRole.ROLE_ADMIN)) {
            log.info("✅ 이미 ROLE_ADMIN 계정이 존재하므로 초기화 생략");
            return;
        }

        String email = env.getProperty("init.admin.email");
        String password = env.getProperty("init.admin.password");

        if (email != null && password != null) {

            // 기본 그룹을 조회 또는 생성
            UserGroupEntity adminGroup = userGroupRepository.findById(1L)
                    .orElseGet(() -> userGroupRepository.save(
                            UserGroupEntity.builder()
                                    .name("관리자 그룹")
                                    .build()
                    ));

            UserEntity admin = UserEntity.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name("관리자")
                    .userRole(UserRole.ROLE_ADMIN)
                    .privacyAgree(true)
                    .pushAgree(true)
                    .userGroupEntity(adminGroup)
                    .build();

            userRepository.save(admin);
            log.info("✅ 최초 관리자 계정 생성 완료: {}", email);
        } else {
            log.warn("❗ 관리자 계정 생성을 위한 환경변수가 설정되지 않았습니다.");
        }
    }
}