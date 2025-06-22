package com.kkimjinoh.global.redis.support;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Lua 스크립트 실행 헬퍼
 */
@Component
@RequiredArgsConstructor
public class LuaExecutor {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisScript<Long> reserveScript;
    private final RedisScript<String> promoteScript;
    private final RedisScript<String> cancelScript;

    /**
     * 예약 시도 (원자적):
     *  0 = 중복, 1 = 성공, 2 = 대기 등록
     */
    public long reserve(String reservationKey, String waitlistKey, String studentNumber) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000); // seconds
        return redisTemplate.execute(reserveScript, List.of(reservationKey, waitlistKey), studentNumber, timestamp);
    }

    /**
     * 대기자 1명 승격 → studentNumber or null
     */
    public String promote(String reservationKey, String waitlistKey) {
        return redisTemplate.execute(promoteScript, List.of(reservationKey, waitlistKey));
    }

    /**
     * 예약 취소 및 승격 (원자적)
     *
     * @return 승격된 studentNumber (없으면 "" 또는 null)
     */
    public String cancel(String reservationKey, String waitlistKey, String studentNumber) {
        return redisTemplate.execute(cancelScript, List.of(reservationKey, waitlistKey), studentNumber);
    }
}