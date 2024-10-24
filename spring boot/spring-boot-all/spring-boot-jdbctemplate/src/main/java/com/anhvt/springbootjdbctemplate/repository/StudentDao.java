package com.anhvt.springbootjdbctemplate.repository;

import com.anhvt.springbootjdbctemplate.model.Student;

import java.util.List;
import java.util.Map;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
public interface StudentDao {
    int add(Student student);

    int update(Student student);

    int deleteBysno(String sno);

    List<Map<String,Object>> queryStudentsListMap();

    Student queryStudentBySno(String sno);
}
