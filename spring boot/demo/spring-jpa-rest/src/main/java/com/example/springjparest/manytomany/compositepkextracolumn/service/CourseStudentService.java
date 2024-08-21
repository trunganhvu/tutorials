package com.example.springjparest.manytomany.compositepkextracolumn.service;

import com.example.springjparest.manytomany.compositepkextracolumn.model.CourseStudent;

public interface CourseStudentService {

    CourseStudent validateAndGetCourseStudent(String courseId, String studentId);

    CourseStudent saveCourseStudent(CourseStudent courseStudent);

    void deleteCourseStudent(CourseStudent courseStudent);
}
