package com.kkimjinoh.movieadmin.domain.user.repository;

import com.kkimjinoh.movieadmin.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 사용자 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}