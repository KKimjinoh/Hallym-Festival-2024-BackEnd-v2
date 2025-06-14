package com.kkimjinoh.movieadmin.domain.auth.repository;

import com.kkimjinoh.movieadmin.domain.auth.entity.AuthCodeEntity;
import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 사용자 인증 코드 Repository
 */
@Repository
public interface AuthCodeRepository extends JpaRepository<AuthCodeEntity, Long> {

    /**
     * 인증코드 값으로 인증코드 엔티티를 조회
     */
    Optional<AuthCodeEntity> findByCode(String code);

}