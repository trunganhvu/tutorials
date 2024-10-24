/**
 * Copyright 2024
 * Name: MysqlStudentDaoImp
 */
package com.anhvt.springbootjdbctemplatemultidatasource.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
@Repository
public class MysqlStudentDaoImp implements MysqlStudentDao{

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getAllStudents() {
        return this.jdbcTemplate.queryForList("select * from STUDENT");
    }

}
