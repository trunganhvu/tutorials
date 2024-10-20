package com.anhvt.springbootbenchmarkinsert.repository;

import com.anhvt.springbootbenchmarkinsert.entity.ParentRelation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
public interface ParentRelationRepository extends JpaRepository<ParentRelation, String> {}

