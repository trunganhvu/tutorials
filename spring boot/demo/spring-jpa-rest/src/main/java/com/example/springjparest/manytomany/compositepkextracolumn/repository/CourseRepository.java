package com.example.springjparest.manytomany.compositepkextracolumn.repository;

import com.example.springjparest.manytomany.compositepkextracolumn.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
