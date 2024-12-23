package org.anhvt.springbootpostgrebackend.model.redis;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("User")
@RequiredArgsConstructor
public class User {
    @Id
    @Indexed
    private Long id;
    private String username;
}
