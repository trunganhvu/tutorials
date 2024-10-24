/**
 * Copyright 2024
 * Name: StudentServiceImp
 */
package com.anhvt.springbootjdbctemplatemultidatasource.service;

import com.anhvt.springbootjdbctemplatemultidatasource.repository.H2StudentDao;
import com.anhvt.springbootjdbctemplatemultidatasource.repository.MysqlStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
@Service("studentService")
public class StudentServiceImp implements StudentService{
    @Autowired
    private H2StudentDao h2StudentDao;
    @Autowired
    private MysqlStudentDao mysqlStudentDao;

    @Override
    public List<Map<String, Object>> getAllStudentsFromOralce() {
        return this.h2StudentDao.getAllStudents();
    }

    @Override
    public List<Map<String, Object>> getAllStudentsFromMysql() {
        return this.mysqlStudentDao.getAllStudents();
    }

}
