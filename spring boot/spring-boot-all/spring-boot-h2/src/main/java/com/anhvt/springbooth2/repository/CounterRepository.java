/**
 * Copyright 2024
 * Name: CounterRepository
 */
package com.anhvt.springbooth2.repository;

import com.anhvt.springbooth2.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/19/2024
 */
public interface CounterRepository extends JpaRepository<Counter, Long> {
}

