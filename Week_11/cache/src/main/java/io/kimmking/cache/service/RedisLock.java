package io.kimmking.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

//@Component
public class RedisLock {

//    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String UNLOCK_LUA;

    private static final long DEFAULT_EXPIRRE = 30L;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    public boolean lock(String lockKey, String lockValue, long expire, TimeUnit timeUnit) {
        RedisCallback<Boolean> callback = (connection) -> connection.set(lockKey.getBytes(StandardCharsets.UTF_8),
                    lockValue.getBytes(StandardCharsets.UTF_8),
                    Expiration.seconds(timeUnit.toSeconds(expire)),
                    RedisStringCommands.SetOption.SET_IF_ABSENT);
        return redisTemplate.execute(callback);
    }

    public boolean unlock(String lockKey, String lockValue) {
        RedisCallback<Boolean> callback = (connection) -> connection.eval(UNLOCK_LUA.getBytes(),
                ReturnType.BOOLEAN, 1, lockKey.getBytes(StandardCharsets.UTF_8),
                lockValue.getBytes(StandardCharsets.UTF_8));
        return redisTemplate.execute(callback);
    }

    public String get(String lockKey) {
        RedisCallback<String> callback = (connection) -> new String(Objects.requireNonNull(connection.get(lockKey.getBytes())), StandardCharsets.UTF_8);
        return redisTemplate.execute(callback);
    }
}
