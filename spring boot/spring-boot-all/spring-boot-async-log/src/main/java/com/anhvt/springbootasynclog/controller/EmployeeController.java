/**
 * Copyright 2024
 * Name: EmployeeController
 */
package com.anhvt.springbootasynclog.controller;

import com.anhvt.springbootasynclog.model.GenericResponse;
import com.anhvt.springbootasynclog.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/31/2024
 */
@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @Autowired
    @Qualifier("employeeService2")
    private EmployeeService employeeService2;

    @GetMapping
    public ResponseEntity<?> getAllEmployee(){
        LOGGER.info("START ==> LOG 1 : Inside EmployeeController getAllEmployee method.");
        log.info("START ==> LOG 2 : Inside EmployeeController getAllEmployee method.");
        log.debug("START ==> LOG 3 : Inside EmployeeController getAllEmployee method.");
        log.warn("START ==> LOG 4 : Inside EmployeeController getAllEmployee method.");
        log.trace("START ==> LOG 5 : Inside EmployeeController getAllEmployee method.");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custome-header", "value")
                .body(GenericResponse.success(employeeService.getAllEmployee()," Get all employee successfully."));

    }

    @GetMapping("/async")
    public ResponseEntity<?> getAllEmployee2(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custome-header", "value")
                .body(GenericResponse.success(employeeService2.getAllEmployee()," Get all employee successfully."));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){
//        LOGGER.info("START ==> LOG 1 : Inside EmployeeController getAllEmployee method.");
        log.info("START ==> LOG 1 : Inside EmployeeController getEmployee method.");
        log.error("getEmployee error");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custome-header", "value")
                .body(GenericResponse.error(employeeService.getAllEmployee()));

    }
}
