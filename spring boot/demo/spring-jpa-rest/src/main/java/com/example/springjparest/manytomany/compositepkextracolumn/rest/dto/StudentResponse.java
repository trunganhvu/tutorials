package com.example.springjparest.manytomany.compositepkextracolumn.rest.dto;

import java.util.Date;
import java.util.List;

public record StudentResponse(String id, String name, List<CourseStudent> courses) {

    public record CourseStudent(Course course, Date registrationDate, Short grade) {

        public record Course(String id, String name) {
        }
    }
}
