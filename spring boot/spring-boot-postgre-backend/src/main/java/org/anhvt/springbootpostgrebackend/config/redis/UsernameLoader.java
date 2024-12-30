package org.anhvt.springbootpostgrebackend.config.redis;

import org.anhvt.springbootpostgrebackend.utils.constant.RedisKey;
import org.anhvt.springbootpostgrebackend.repository.redis.RedisRepository;
import org.anhvt.springbootpostgrebackend.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsernameLoader implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(UsernameLoader.class);

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Check usernames in redis...");
        if (!redisRepository.hasKeyWithPrefix(RedisKey.REGISTERED_USER_KEY)) {
            LOGGER.info("Start loading username to redis...");
            userService.getUsers().forEach(user -> redisRepository.registerUsername(user.getUsername()));
            LOGGER.info("Loaded username to redis...");
        }
        LOGGER.info("End loading username to redis...");
    }
}
