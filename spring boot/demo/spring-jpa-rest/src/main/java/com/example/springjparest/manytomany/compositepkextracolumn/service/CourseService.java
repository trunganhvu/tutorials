package com.example.springjparest.manytomany.compositepkextracolumn.service;

import com.example.springjparest.manytomany.compositepkextracolumn.model.Course;

public interface CourseService {

    Course validateAndGetCourse(String id);

    Course saveCourse(Course course);

    void deleteCourse(Course course);
}
