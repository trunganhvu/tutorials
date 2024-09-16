/**
 * Copyright 2024
 * Name: EmployeeService
 */
package com.anhvt.springbootlogging.service;

import com.anhvt.springbootlogging.controller.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
public interface EmployeeService {
    public List<?> getAllEmployee();
}
