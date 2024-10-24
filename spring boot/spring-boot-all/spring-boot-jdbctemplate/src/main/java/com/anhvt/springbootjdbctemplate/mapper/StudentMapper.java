/**
 * Copyright 2024
 * Name: StudentMapper
 */
package com.anhvt.springbootjdbctemplate.mapper;

import com.anhvt.springbootjdbctemplate.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setSno(rs.getString("sno"));
        student.setName(rs.getString("sname"));
        student.setSex(rs.getString("ssex"));
        return student;
    }
}
