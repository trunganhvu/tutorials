/**
 * Copyright 2024
 * Name: Book
 */
package com.anhvt.springbootannotation.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/12/2024
 */
@Entity(name = "tbl_book")
public class Book {
    @Id
    private int id;
    private String name;
}
