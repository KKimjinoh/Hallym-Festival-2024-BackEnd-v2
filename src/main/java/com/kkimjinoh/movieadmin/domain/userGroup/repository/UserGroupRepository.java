package com.kkimjinoh.movieadmin.domain.userGroup.repository;

import com.kkimjinoh.movieadmin.domain.reservation.entity.ReservationStatus;
import com.kkimjinoh.movieadmin.domain.userGroup.entity.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 사용자 그룹 코드 Repository
 */
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long> { }