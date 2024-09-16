/**
 * Copyright 2024
 * Name: EmployeeServiceImpl
 */
package com.anhvt.springbootlogging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public List<?> getAllEmployee() {
        LOGGER.info("START ==> LOG 1 : Inside EmployeeService getAllEmployee method.");
        return List.of("A", "B", "C", "D");
    }
}
