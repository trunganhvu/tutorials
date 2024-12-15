package org.anhvt.schoolservice.repository;

import org.anhvt.schoolservice.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}

