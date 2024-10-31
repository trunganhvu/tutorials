/**
 * Copyright 2024
 * Name: EmployeeServiceImpl2
 */
package com.anhvt.springbootasynclog.service;


import com.anhvt.springbootasynclog.config.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/31/2024
 */
@Service
@Qualifier("employeeService2")
public class EmployeeServiceImpl2 implements EmployeeService {
    @Autowired
    private Logger LOGGER;

    public List<?> getAllEmployee() {
        LOGGER.logMessage("START ==> LOG 2 : Inside EmployeeService2 getAllEmployee method.");
        return List.of("A", "B", "C", "D");
    }
}
