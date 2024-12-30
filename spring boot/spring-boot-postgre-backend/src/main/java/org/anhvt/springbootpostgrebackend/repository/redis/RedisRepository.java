package org.anhvt.springbootpostgrebackend.repository.redis;

import org.anhvt.springbootpostgrebackend.utils.StringUtils;
import org.anhvt.springbootpostgrebackend.utils.constant.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class RedisRepository {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean hasKeyWithPrefix(String prefix) {
        Set<String> keys = redisTemplate.keys(prefix + "*");
        return !keys.isEmpty();
    }

    public void registerUsername(String username) {
        long bitOffset = StringUtils.crc32(username);
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.setBit(RedisKey.REGISTERED_USER_KEY.getBytes(), bitOffset, true);
            return null;
        });
    }
}
