package com.kkimjinoh.movieadmin.domain.auth.entity;

import com.kkimjinoh.global.entity.DateEntity;
import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

/**
 * 사용자 인증 코드 Entity
 */
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_auth_code")
public class AuthCodeEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "인증 코드 ID", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "가입할 사용자 이메일", example = "example@hallym.ac.kr")
    private String email;

    @Column(nullable = false)
    @Schema(description = "발급된 인증 코드", example = "8A7D3F")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    @Schema(description = "코드를 발급한 관리자")
    private UserEntity createdByUserId;

    @Column(nullable = false)
    @Schema(description = "인증 코드 만료 시간", example = "2025-06-06T00:00:00")
    private LocalDateTime expiredAt;
}
