package com.anhvt.springboottransaction.repository;

import com.anhvt.springboottransaction.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public interface CounterRepository extends JpaRepository<Counter, Long>{
}
