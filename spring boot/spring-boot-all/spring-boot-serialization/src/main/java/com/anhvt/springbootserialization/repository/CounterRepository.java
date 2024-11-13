package com.anhvt.springbootserialization.repository;

import com.anhvt.springbootserialization.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 11/13/2024
 */
public interface CounterRepository extends JpaRepository<Counter, Long> {
}
