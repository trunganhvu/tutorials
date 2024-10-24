package com.anhvt.springbootjdbctemplatemultidatasource.service;

import java.util.List;
import java.util.Map;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
public interface StudentService {
    List<Map<String, Object>> getAllStudentsFromOralce();

    List<Map<String, Object>> getAllStudentsFromMysql();
}
