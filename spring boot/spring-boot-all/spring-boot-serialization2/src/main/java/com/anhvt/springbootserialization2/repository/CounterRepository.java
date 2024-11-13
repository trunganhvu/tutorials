package com.anhvt.springbootserialization2.repository;

import com.anhvt.springbootserialization2.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 11/13/2024
 */
public interface CounterRepository extends JpaRepository<Counter, Long> {
}
