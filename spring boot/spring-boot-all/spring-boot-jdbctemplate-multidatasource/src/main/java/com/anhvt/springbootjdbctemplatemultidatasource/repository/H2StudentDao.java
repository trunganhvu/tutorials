/**
 * Copyright 2024
 * Name: H2StudentDao
 */
package com.anhvt.springbootjdbctemplatemultidatasource.repository;

import java.util.List;
import java.util.Map;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
public interface  H2StudentDao {
    List<Map<String, Object>> getAllStudents();
}
