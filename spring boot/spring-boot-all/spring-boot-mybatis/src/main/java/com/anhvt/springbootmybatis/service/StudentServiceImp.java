/**
 * Copyright 2024
 * Name: StudentServiceImp
 */
package com.anhvt.springbootmybatis.service;

import com.anhvt.springbootmybatis.mapper.StudentMapper;
import com.anhvt.springbootmybatis.model.Student;
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
    private StudentMapper studentMapper;

    @Override
    public int add(Student student) {
        return this.studentMapper.add(student);
    }

    @Override
    public int update(Student student) {
        return this.studentMapper.update(student);
    }

    @Override
    public int deleteBysno(String sno) {
        return this.studentMapper.deleteBysno(sno);
    }

    @Override
    public List<Map<String, Object>> queryStudentListMap() {
        return this.studentMapper.queryStudentList();
    }

    @Override
    public Student queryStudentBySno(String sno) {
        return this.studentMapper.queryStudentBySno(sno);
    }
}
