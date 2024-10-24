/**
 * Copyright 2024
 * Name: StudentController
 */
package com.anhvt.springbootjdbctemplate.controller;

import com.anhvt.springbootjdbctemplate.model.Student;
import com.anhvt.springbootjdbctemplate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/{sno}", method = RequestMethod.GET)
    public Student queryStudentBySno(@PathVariable String sno) {
        return this.studentService.queryStudentBySno(sno);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Map<String, Object>> queryAllStudent() {
        return this.studentService.queryStudentListMap();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int saveStudent(String sno,String name,String sex) {
        Student student = new Student();
        student.setSno(sno);
        student.setName(name);
        student.setSex(sex);
        return this.studentService.add(student);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public int deleteStudentBySno(String sno) {
        return this.studentService.deleteBysno(sno);
    }
}
