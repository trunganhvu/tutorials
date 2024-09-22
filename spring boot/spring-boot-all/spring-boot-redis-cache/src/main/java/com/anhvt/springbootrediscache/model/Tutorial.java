/**
 * Copyright 2024
 * Name: Tutorial
 */
package com.anhvt.springbootrediscache.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
@Entity
@Table(name = "tutorials")
@Data
public class Tutorial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;
}
