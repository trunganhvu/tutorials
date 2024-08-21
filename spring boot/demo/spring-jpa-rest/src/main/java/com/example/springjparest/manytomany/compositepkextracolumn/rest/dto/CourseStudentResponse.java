package com.example.springjparest.manytomany.compositepkextracolumn.rest.dto;

import java.util.Date;

public record CourseStudentResponse(Course course, Student student, Date registrationDate, Short grade) {

    public record Course(String id, String name) {
    }

    public record Student(String id, String name) {
    }
}
