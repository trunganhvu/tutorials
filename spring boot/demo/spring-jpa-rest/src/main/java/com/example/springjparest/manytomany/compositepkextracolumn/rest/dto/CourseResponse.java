package com.example.springjparest.manytomany.compositepkextracolumn.rest.dto;

import java.util.Date;
import java.util.List;

public record CourseResponse(String id, String name, List<CourseStudent> students) {

    public record CourseStudent(Student student, Date registrationDate, Short grade) {

        public record Student(String id, String name) {
        }
    }
}
