package org.anhvt.springbootpostgrebackend.queue.sub;

import lombok.RequiredArgsConstructor;
import org.anhvt.springbootpostgrebackend.utils.StringUtils;
import org.anhvt.springbootpostgrebackend.utils.constant.RedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRedisMessageSubscriber implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRedisMessageSubscriber.class);
    public static List<String> messageList = new ArrayList<>();

    private final RedisTemplate<String, Object> redisTemplate;

    public void onMessage(final Message message, final byte[] pattern) {
        messageList.add(message.toString());
        LOGGER.info("[SUB] UserRedisMessageSubscriber Message received: {}", new String(message.getBody()));
        registerUsername(new String(message.getBody()));
    }

    public void registerUsername(String username) {
        long bitOffset = StringUtils.crc32(username);
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.setBit(RedisKey.REGISTERED_USER_KEY.getBytes(), bitOffset, true);
            return null;
        });
    }
}