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

//    public RedisMessagePublisher() {
//    }
//
//    public RedisMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
//        this.redisTemplate = redisTemplate;
//        this.topic = topic;
//    }

    public void publishUser(final String message) {
        redisTemplate.convertAndSend(topicUser.getTopic(), message);
    }

    @Override
    public void publishArticle(String message) {
        redisTemplate.convertAndSend(topicArticle.getTopic(), message);
    }
}