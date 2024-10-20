/**
 * Copyright 2024
 * Name: ChildNoRelation
 */
package com.anhvt.springbootbenchmarkinsert.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@Entity
@Data
public class ChildNoRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Transient
    private ParentNoRelation parent;

    private String description;
    private String note;
}
