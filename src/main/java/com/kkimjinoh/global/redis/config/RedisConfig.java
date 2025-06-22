package com.kkimjinoh.global.redis.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * Redis 설정 클래스
 * - Redis 연결 설정 및 RedisTemplate Bean 등록
 * - Lua 스크립트 관련 RedisScript Bean 등록
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private int port;
    @Value("${spring.data.redis.password}")
    private String password;
    @Value("${spring.data.redis.timeout}")
    private long timeoutMs;

    /**
     * LettuceConnectionFactory (password / timeout)
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration server = new RedisStandaloneConfiguration();
        server.setHostName(host);
        server.setPort(port);
        if (StringUtils.hasText(password)) {
            server.setPassword(RedisPassword.of(password));
        }
        LettuceClientConfiguration client = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(timeoutMs))
                .build();
        return new LettuceConnectionFactory(server, client);
    }

    /**
     * String 기반 RedisTemplate (UTF‑8)
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> tpl = new RedisTemplate<>();
        tpl.setConnectionFactory(cf);
        StringRedisSerializer s = new StringRedisSerializer();
        tpl.setDefaultSerializer(s);
        tpl.setKeySerializer(s);
        tpl.setValueSerializer(s);
        tpl.setHashKeySerializer(s);
        tpl.setHashValueSerializer(s);
        return tpl;
    }


    /**
     * 예약 처리 Lua 스크립트 등록
     */
    @Bean
    public RedisScript<Long> reserveScript() {
        return RedisScript.of(new ClassPathResource("redis/script/reserve.lua"), Long.class);
    }

    /**
     * 대기자 승격 처리 Lua 스크립트 등록
     */
    @Bean
    public RedisScript<String> promoteScript() {
        return RedisScript.of(new ClassPathResource("redis/script/promote.lua"), String.class);
    }

    /**
     * 예약 취소 처리 Lua 스크립트 등록
     */
    @Bean
    public RedisScript<String> cancelScript() {
        return RedisScript.of(new ClassPathResource("redis/script/cancel.lua"), String.class);
    }
}