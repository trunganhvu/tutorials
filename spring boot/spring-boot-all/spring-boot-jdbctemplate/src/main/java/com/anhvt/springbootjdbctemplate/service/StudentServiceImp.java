/**
 * Copyright 2024
 * Name: StudentServiceImp
 */
package com.anhvt.springbootjdbctemplate.service;

import com.anhvt.springbootjdbctemplate.model.Student;
import com.anhvt.springbootjdbctemplate.repository.StudentDao;
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
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public int add(Student student) {
        return this.studentDao.add(student);
    }

    @Override
    public int update(Student student) {
        return this.studentDao.update(student);
    }

    @Override
    public int deleteBysno(String sno) {
        return this.studentDao.deleteBysno(sno);
    }

    @Override
    public List<Map<String, Object>> queryStudentListMap() {
        return this.studentDao.queryStudentsListMap();
    }

    @Override
    public Student queryStudentBySno(String sno) {
        return this.studentDao.queryStudentBySno(sno);
    }

}
