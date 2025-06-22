-- KEYS[1] = reservation:<date>
-- KEYS[2] = waitlist:<date> (ZSET)

local candidates = redis.call("ZRANGE", KEYS[2], 0, 0)  -- 가장 먼저 들어온 한 명
if #candidates > 0 then
    redis.call("ZREM", KEYS[2], candidates[1])          -- waitlist 에서 제거
    redis.call("SADD", KEYS[1], candidates[1])          -- 예약자 SET 에 추가
    return candidates[1]                                -- 승격된 학번 반환
end
return nil                                              -- 대기자 없음
