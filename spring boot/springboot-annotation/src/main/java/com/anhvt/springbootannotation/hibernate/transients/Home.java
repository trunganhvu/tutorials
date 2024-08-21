/**
 * Copyright 2024
 * Name: Home
 */
package com.anhvt.springbootannotation.hibernate.transients;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/15/2024
 */
@Entity(name = "tbl_home")
@Table
public class Home {
    @Id
    private Long id;

    private String name;

    private String cost;

    @Transient
    private String version;
}
