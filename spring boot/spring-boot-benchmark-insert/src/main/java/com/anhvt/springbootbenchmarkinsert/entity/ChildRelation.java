/**
 * Copyright 2024
 * Name: ChildRelation
 */
package com.anhvt.springbootbenchmarkinsert.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@Entity
@Data
public class ChildRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private ParentRelation parent;

    private String description;
    private String note;
}
