package com.kkimjinoh.movieadmin.domain.user.repository;

import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import com.kkimjinoh.movieadmin.domain.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 사용자 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * 이메일로 사용자 조회
     */
    Optional<UserEntity> findByEmail(String email);


    /**
     * 특정 역할(Role)을 가진 사용자가 하나라도 존재하는지 확인
     */
    boolean existsByUserRole(UserRole userRole);
}