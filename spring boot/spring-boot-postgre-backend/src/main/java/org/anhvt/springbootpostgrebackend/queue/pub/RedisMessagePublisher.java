package org.anhvt.springbootpostgrebackend.queue.pub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher implements MessagePublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessagePublisher.class);

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
        LOGGER.info("[PUB] Public message blacklist start: {}", message);
        redisTemplate.convertAndSend(topicSessionBlacklist.getTopic(), message);
        LOGGER.info("[PUB]Public message blacklist end: {}", message);
    }
}