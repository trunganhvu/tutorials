/**
 * Copyright 2024
 * Name: Counter
 */
package com.anhvt.springbooth2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/19/2024
 */
@Data
@Entity
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int counter;

    @Version
    private Integer version;
}
