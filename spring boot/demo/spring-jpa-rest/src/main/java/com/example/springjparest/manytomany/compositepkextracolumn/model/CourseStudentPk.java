package com.example.springjparest.manytomany.compositepkextracolumn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentPk implements Serializable {

    private String course;
    private String student;
}