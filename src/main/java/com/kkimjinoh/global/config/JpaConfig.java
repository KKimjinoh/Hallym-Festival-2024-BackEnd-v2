package com.kkimjinoh.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing( @CreatedDate, @LastModifiedDate 등 ) 활성화 설정
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}