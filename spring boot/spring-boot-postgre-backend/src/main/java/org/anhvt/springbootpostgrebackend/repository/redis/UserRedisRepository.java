package org.anhvt.springbootpostgrebackend.repository.redis;

import org.anhvt.springbootpostgrebackend.model.redis.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<User, Long> {
}
