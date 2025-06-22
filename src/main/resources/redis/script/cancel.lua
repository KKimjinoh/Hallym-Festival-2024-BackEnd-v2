-- KEYS[1] = reservation:<date>
-- KEYS[2] = waitlist:<date>
-- ARGV[1] = studentNumber

local removed = redis.call("SREM", KEYS[1], ARGV[1])
if removed == 1 then
    local candidates = redis.call("ZRANGE", KEYS[2], 0, 0)
    if #candidates > 0 then
        redis.call("ZREM", KEYS[2], candidates[1])
        redis.call("SADD", KEYS[1], candidates[1])
        return candidates[1]
    end
    return ""
end

local removedFromWait = redis.call("ZREM", KEYS[2], ARGV[1])
if removedFromWait == 1 then
    return ""
end
return nil
