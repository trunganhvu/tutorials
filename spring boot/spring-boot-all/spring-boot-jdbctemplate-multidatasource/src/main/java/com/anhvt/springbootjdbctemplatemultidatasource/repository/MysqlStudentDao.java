package com.anhvt.springbootjdbctemplatemultidatasource.repository;

import java.util.List;
import java.util.Map;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
public interface MysqlStudentDao {
    List<Map<String, Object>> getAllStudents();
}
