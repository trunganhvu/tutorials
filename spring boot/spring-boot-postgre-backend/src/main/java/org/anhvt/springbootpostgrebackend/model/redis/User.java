package org.anhvt.springbootpostgrebackend.model.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
@RequiredArgsConstructor
public class User {
    private Long id;
    private String username;
}
