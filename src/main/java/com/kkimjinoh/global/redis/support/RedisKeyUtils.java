package com.kkimjinoh.global.redis.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 날짜별 Redis 키 유틸리티
 */
public final class RedisKeyUtils {
    private RedisKeyUtils() {}
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String reservationKey(LocalDate d) { return "reservation:" + F.format(d); }
    public static String waitlistKey(LocalDate d)    { return "waitlist:"    + F.format(d); }
}