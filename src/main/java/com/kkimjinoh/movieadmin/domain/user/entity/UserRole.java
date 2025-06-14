package com.kkimjinoh.movieadmin.domain.user.entity;

/**
 * 사용자 권한(Role) 구분 Enum
 */
public enum UserRole {
    // 전체 권한
    ROLE_ADMIN,
    // 관리 권한
    ROLE_MANAGER,
    // 일부 권한
    ROLE_OPERATOR
}