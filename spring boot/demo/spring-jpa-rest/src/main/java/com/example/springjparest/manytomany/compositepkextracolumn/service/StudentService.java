package com.example.springjparest.manytomany.compositepkextracolumn.service;

import com.example.springjparest.manytomany.compositepkextracolumn.model.Student;

public interface StudentService {

    Student validateAndGetStudent(String id);

    Student saveStudent(Student student);

    void deleteStudent(Student student);
}
