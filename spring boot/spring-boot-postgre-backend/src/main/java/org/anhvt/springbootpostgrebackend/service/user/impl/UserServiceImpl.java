package org.anhvt.springbootpostgrebackend.service.user.impl;

import org.anhvt.springbootpostgrebackend.entity.auth.User;
import org.anhvt.springbootpostgrebackend.queue.pub.RedisMessagePublisher;
import org.anhvt.springbootpostgrebackend.repository.UserRepository;
import org.anhvt.springbootpostgrebackend.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisMessagePublisher redisPublisher;


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        LOGGER.info("getUserByUsername1: {}", username);
        redisPublisher.publishUser(username);
        LOGGER.info("getUserByUsername2: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User validateAndGetUserByUsername(String username) {
        LOGGER.info("validateAndGetUserByUsername: {}", username);
        return getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException(String.format("User with username %s not found", username)));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
