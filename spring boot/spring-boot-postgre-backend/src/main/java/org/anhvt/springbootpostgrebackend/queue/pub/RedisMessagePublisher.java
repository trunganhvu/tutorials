package org.anhvt.springbootpostgrebackend.queue.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher implements MessagePublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChannelTopic topicUser;

    @Autowired
    private ChannelTopic topicArticle;

    @Autowired
    private ChannelTopic topicSessionBlacklist;

    @Override
    public void publishUser(final String message) {
        redisTemplate.convertAndSend(topicUser.getTopic(), message);
    }

    @Override
    public void publishArticle(String message) {
        redisTemplate.convertAndSend(topicArticle.getTopic(), message);
    }

    @Override
    public void publishSessionBlacklist(String message) {
        System.out.println("pub logout");
        redisTemplate.convertAndSend(topicSessionBlacklist.getTopic(), message);
    }
}