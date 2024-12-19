package org.anhvt.springbootpostgrebackend.queue.sub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Service
@Component
public class ArticleRedisMessageSubscriber implements MessageListener {

    public static List<String> messageList = new ArrayList<>();

    public void onMessage(final Message message, final byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("ArticleRedisMessageSubscriber Message received: " + new String(message.getBody()));
    }
}