package org.anhvt.springbootpostgrebackend.queue.sub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleRedisMessageSubscriber implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleRedisMessageSubscriber.class);
    public static List<String> messageList = new ArrayList<>();

    public void onMessage(final Message message, final byte[] pattern) {
        LOGGER.info("[SUB] Message received: {}", new String(message.getBody()));
        messageList.add(message.toString());
    }
}