/**
 * Copyright 2024
 * Name: Counter
 */
package com.anhvt.springbootserialization.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;

import java.time.Instant;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 11/13/2024
 */
@Data
@Entity
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int counter;

    private Instant start;

    @Version
    private Integer version;
}
