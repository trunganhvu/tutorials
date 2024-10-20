/**
 * Copyright 2024
 * Name: ParentRelation
 */
package com.anhvt.springbootbenchmarkinsert.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@Entity
@Data
public class ParentRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private int deleted;
    private Instant createdBy;
    private Instant updatedBy;
}
