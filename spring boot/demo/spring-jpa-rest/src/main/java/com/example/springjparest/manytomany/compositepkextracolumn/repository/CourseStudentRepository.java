package com.example.springjparest.manytomany.compositepkextracolumn.repository;

import com.example.springjparest.manytomany.compositepkextracolumn.model.CourseStudent;
import com.example.springjparest.manytomany.compositepkextracolumn.model.CourseStudentPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudentPk> {
}
