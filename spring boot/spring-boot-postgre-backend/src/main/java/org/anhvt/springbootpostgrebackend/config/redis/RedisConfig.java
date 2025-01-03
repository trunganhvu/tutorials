package org.anhvt.springbootpostgrebackend.config.redis;

import org.anhvt.springbootpostgrebackend.queue.sub.ArticleRedisMessageSubscriber;
import org.anhvt.springbootpostgrebackend.queue.sub.SessionBlackListRedisMessageSubscriber;
import org.anhvt.springbootpostgrebackend.queue.sub.UserRedisMessageSubscriber;
import org.anhvt.springbootpostgrebackend.utils.constant.RedisKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@PropertySource("classpath:application.properties")
@EnableRedisRepositories("org.anhvt.springbootpostgrebackend.repository.redis")
@ComponentScan("org.anhvt.springbootpostgrebackend")
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName(redisHost);
        jedisConFactory.setPort(redisPort);
        return jedisConFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    MessageListenerAdapter messageUserListener() {
        return new MessageListenerAdapter(new UserRedisMessageSubscriber(redisTemplate()));
    }

    @Bean
    MessageListenerAdapter messageArticleListener() {
        return new MessageListenerAdapter(new ArticleRedisMessageSubscriber());
    }

    @Bean
    MessageListenerAdapter messageSessionBlackListListener() {
        return new MessageListenerAdapter(new SessionBlackListRedisMessageSubscriber(redisTemplate()));
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageUserListener(), topicUser());
        container.addMessageListener(messageUserListener(), topicUsername());
        container.addMessageListener(messageArticleListener(), topicArticle());
        container.addMessageListener(messageSessionBlackListListener(), topicSessionBlacklist());
        return container;
    }

    @Bean
    ChannelTopic topicUser() {
        return new ChannelTopic(RedisKey.TOPIC_USER);
    }

    @Bean
    ChannelTopic topicUsername() {
        return new ChannelTopic(RedisKey.TOPIC_USERNAME);
    }

    @Bean
    ChannelTopic topicArticle() {
        return new ChannelTopic(RedisKey.TOPIC_ARTICLE);
    }

    @Bean
    ChannelTopic topicSessionBlacklist() {
        return new ChannelTopic(RedisKey.TOPIC_SESSION_BLACKLIST);
    }
}
