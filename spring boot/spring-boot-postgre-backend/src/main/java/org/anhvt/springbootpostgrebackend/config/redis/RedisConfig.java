package org.anhvt.springbootpostgrebackend.config.redis;

import org.anhvt.springbootpostgrebackend.queue.sub.ArticleRedisMessageSubscriber;
import org.anhvt.springbootpostgrebackend.queue.sub.UserRedisMessageSubscriber;
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
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
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
        return new MessageListenerAdapter(new UserRedisMessageSubscriber());
    }

    @Bean
    MessageListenerAdapter messageArticleListener() {
        return new MessageListenerAdapter(new ArticleRedisMessageSubscriber());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageUserListener(), topicUser());
        container.addMessageListener(messageArticleListener(), topicArticle());
        return container;
    }

//    @Bean
//    MessagePublisher redisPublisher() {
//        return new RedisMessagePublisher(redisTemplate(), topic());
//    }

    @Bean
    ChannelTopic topicUser() {
        return new ChannelTopic("pubsub:user");
    }

    @Bean
    ChannelTopic topicArticle() {
        return new ChannelTopic("pubsub:article");
    }
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
//        jedisConFactory.setHostName(redisHost);
//        jedisConFactory.setPort(redisPort);
//        return jedisConFactory;
//    }
//
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//        return template;
//    }
//
//    @Bean
//    public MessageListenerAdapter messageListener() {
//        return new MessageListenerAdapter(new RedisMessageSubscriber());
//    }
//
//    @Bean
//    public RedisMessageListenerContainer redisContainer() {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(jedisConnectionFactory());
//
//        // Đăng ký tất cả các topic trong danh sách
//        topics().values().forEach(topic ->
//                container.addMessageListener(messageListener(), topic)
//        );
//
//        return container;
//    }
//
//    @Bean
//    public Map<String, ChannelTopic> topics() {
//        Map<String, ChannelTopic> topicsMap = new HashMap<>();
//        topicsMap.put("user", new ChannelTopic("pubsub:user"));
//        topicsMap.put("article", new ChannelTopic("pubsub:article"));
//        topicsMap.put("product", new ChannelTopic("pubsub:product"));
//        return topicsMap;
//    }
//
//    @Bean
//    public MessagePublisher redisPublisher() {
//        return new RedisMessagePublisher(redisTemplate(), topics().get("user")); // Mặc định cho user
//    }
}
