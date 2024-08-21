/**
 * Copyright 2024
 * Name: Employee
 */
package com.anhvt.springbootannotation.hibernate.generatedvalue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/14/2024
 */
@Entity(name = "tbl_employee")
@Table
@IdClass(EmployeeId.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fieldId1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int fieldId2;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String fieldId3;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int fieldId4;
}
