package com.kkimjinoh.movieadmin.domain.user.entity;

import com.kkimjinoh.global.entity.DateEntity;
import com.kkimjinoh.movieadmin.domain.userGroup.entity.UserGroupEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

/**
 * 사용자 Entity
 */
@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@Table(name = "user")
public class UserEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "사용자 ID", example = "1")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "이메일", example = "example@hallym.ac.kr")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "사용자 역할", example = "ROLE_MANAGER")
    private UserRole userRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_group_id", nullable = false)
    @Schema(description = "사용자 소속 그룹")
    private UserGroupEntity userGroupEntity;

    @Column(nullable = true)
    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Column(nullable = true)
    @Schema(description = "비밀번호 (BCrypt 암호화)", example = "$2a$10$...")
    private String password;

    @Column(nullable = true)
    @Schema(description = "학번", example = "202412345")
    private String studentNumber;

    @Column(nullable = true)
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "boolean default false")
    @Schema(description = "개인정보 수집 동의 여부", example = "true")
    private boolean privacyAgree;

    @Column(nullable = false, columnDefinition = "boolean default false")
    @Schema(name = "pushAgree", description = "알림 수신 동의 여부", example = "true")
    private boolean pushAgree;

    @Column(nullable = true)
    @Schema(description = "최근 로그인 시각", example = "2025-06-05T21:00:00")
    private LocalDateTime loginDate;

}
