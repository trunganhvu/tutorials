/**
 * Copyright 2024
 * Name: Stack
 */
package com.anhvt.springbootannotation.hibernate.id;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/12/2024
 */
@Entity
@Table(name = "tbl_stack")
public class Stack {
    @Id
    private int id;

    @Id
    private int pk;

    private String name;
}
