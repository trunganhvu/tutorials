package org.anhvt.springbootpostgrebackend.queue.sub;

import lombok.RequiredArgsConstructor;
import org.anhvt.springbootpostgrebackend.utils.constant.RedisKey;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionBlackListRedisMessageSubscriber implements MessageListener {
    private final RedisTemplate<String, Object> redisTemplate;

    public void onMessage(final Message message, final byte[] pattern) {
        String msg = new String(message.getBody());
        if (Strings.isBlank(msg)) return;
        String username = msg.split("\\|")[0];
        String refreshTokenTopChar = msg.split("\\|")[1];
        System.out.println("SessionBlackListRedisMessageSubscriber-start: " + username);
        redisTemplate.opsForSet().add(RedisKey.SESSION_BLACKLIST + username, refreshTokenTopChar);
        System.out.println("SessionBlackListRedisMessageSubscriber-end: " + refreshTokenTopChar);
    }
}