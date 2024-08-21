/**
 * Copyright 2024
 * Name: Story
 */
package com.anhvt.springbootannotation.hibernate.table;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/12/2024
 */
@Entity
@Table(
        name = "tbl_story",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        },
        indexes = {
                @Index(name = "idx_email_name", columnList = "email, name")
        }
)
public class Story {
    @Id
    private int id;
    private String name;
    private String email;
}
