package org.anhvt.springbootpostgrebackend.queue.sub;

import lombok.RequiredArgsConstructor;
import org.anhvt.springbootpostgrebackend.utils.constant.RedisKey;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SessionBlackListRedisMessageSubscriber implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionBlackListRedisMessageSubscriber.class);

    @Value("${app.jwt.access.token.expiration}")
    private Long jwtAccessTokenExpiration;

    private final RedisTemplate<String, Object> redisTemplate;

    public void onMessage(final Message message, final byte[] pattern) {
        LOGGER.info("[SUB] SessionBlackListRedisMessageSubscriber Start");
        String msg = new String(message.getBody());
        if (Strings.isBlank(msg)) return;
        String username = msg.split("\\|")[0];
        String refreshTokenTopChar = msg.split("\\|")[1];

        LOGGER.info("[SUB] SessionBlackListRedisMessageSubscriber Data: {}", username);

        String key = RedisKey.SESSION_BLACKLIST + username;
        redisTemplate.opsForSet().add(key, refreshTokenTopChar);
        redisTemplate.expire(key, jwtAccessTokenExpiration, TimeUnit.MILLISECONDS);
        LOGGER.info("[SUB] SessionBlackListRedisMessageSubscriber End");
    }
}