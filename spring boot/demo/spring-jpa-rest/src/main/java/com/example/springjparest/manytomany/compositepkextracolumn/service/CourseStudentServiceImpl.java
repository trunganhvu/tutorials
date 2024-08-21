package com.example.springjparest.manytomany.compositepkextracolumn.service;

import com.example.springjparest.manytomany.compositepkextracolumn.exception.CourseStudentNotFoundException;
import com.example.springjparest.manytomany.compositepkextracolumn.model.CourseStudent;
import com.example.springjparest.manytomany.compositepkextracolumn.model.CourseStudentPk;
import com.example.springjparest.manytomany.compositepkextracolumn.repository.CourseStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseStudentServiceImpl implements CourseStudentService {

    private final CourseStudentRepository courseStudentRepository;

    @Override
    public CourseStudent validateAndGetCourseStudent(String courseId, String studentId) {
        CourseStudentPk courseStudentPk = new CourseStudentPk(courseId, studentId);
        return courseStudentRepository.findById(courseStudentPk)
                .orElseThrow(() -> new CourseStudentNotFoundException(courseStudentPk));
    }

    @Override
    public CourseStudent saveCourseStudent(CourseStudent courseStudent) {
        return courseStudentRepository.save(courseStudent);
    }

    @Override
    public void deleteCourseStudent(CourseStudent courseStudent) {
        courseStudentRepository.delete(courseStudent);
    }
}
