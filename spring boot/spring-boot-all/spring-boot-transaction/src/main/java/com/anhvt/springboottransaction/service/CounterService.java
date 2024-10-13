/**
 * Copyright 2024
 * Name: CounterService
 */
package com.anhvt.springboottransaction.service;

import com.anhvt.springboottransaction.entity.Counter;
import com.anhvt.springboottransaction.repository.CounterRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
@Service
public class CounterService {
    @Autowired
    private CounterRepository counterRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Counter getCounter() {
        Counter c = counterRepository.findById(1L).orElseGet(null);
        System.out.println(c.getCounter());
        return c;
    }

    @Retryable(
            value = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000) // Thời gian chờ giữa các lần retry
    )
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    public Counter incrementCount() throws InterruptedException {
        Counter counter = counterRepository.findById(1L).orElseGet(null);
        counter.setCounter(counter.getCounter() + 1);
        counter = counterRepository.save(counter);
        System.out.println(counter.getCounter());
        Thread.sleep(8000);
        System.out.println("sleep done");
        return counter;
    }
}
