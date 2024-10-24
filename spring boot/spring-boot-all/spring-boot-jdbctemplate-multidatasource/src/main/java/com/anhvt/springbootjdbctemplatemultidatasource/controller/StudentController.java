/**
 * Copyright 2024
 * Name: StudentController
 */
package com.anhvt.springbootjdbctemplatemultidatasource.controller;

import com.anhvt.springbootjdbctemplatemultidatasource.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("h2")
    public List<Map<String, Object>> queryStudentsFromH2(){
        return this.studentService.getAllStudentsFromOralce();
    }

    @RequestMapping("mysql")
    public List<Map<String, Object>> queryStudentsFromMysql(){
        return this.studentService.getAllStudentsFromMysql();
    }
}
