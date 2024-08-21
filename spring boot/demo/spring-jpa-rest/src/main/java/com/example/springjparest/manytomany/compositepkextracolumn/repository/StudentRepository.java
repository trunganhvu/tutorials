package com.example.springjparest.manytomany.compositepkextracolumn.repository;

import com.example.springjparest.manytomany.compositepkextracolumn.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
