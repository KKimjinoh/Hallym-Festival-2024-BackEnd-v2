-- KEYS[1] = reservation:<date>
-- KEYS[2] = waitlist:<date>
-- ARGV[1] = studentNumber
-- ARGV[2] = timestamp

-- 중복 체크
if redis.call("SISMEMBER", KEYS[1], ARGV[1]) == 1 then
    return 0
end
if redis.call("ZSCORE", KEYS[2], ARGV[1]) then
    return 0
end

-- 인원수 체크
local current = redis.call("SCARD", KEYS[1])
if current < 100 then
    redis.call("SADD", KEYS[1], ARGV[1])
    return 1
else
    redis.call("ZADD", KEYS[2], ARGV[2], ARGV[1])
    return 2
end
