package com.anhvt.springbootbenchmarkinsert.repository;

import com.anhvt.springbootbenchmarkinsert.entity.ChildNoRelation;
import com.anhvt.springbootbenchmarkinsert.entity.ParentNoRelation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
public interface ParentNoRelationRepository extends JpaRepository<ParentNoRelation, String> {}
