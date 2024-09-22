/**
 * Copyright 2024
 * Name: TutorialRepository
 */
package com.anhvt.springbootahcache.repository;

import com.anhvt.springbootahcache.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByTitleContaining(String title);
}
