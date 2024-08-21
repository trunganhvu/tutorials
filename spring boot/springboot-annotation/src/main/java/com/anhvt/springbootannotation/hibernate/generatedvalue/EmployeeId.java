/**
 * Copyright 2024
 * Name: EmployeeId
 */
package com.anhvt.springbootannotation.hibernate.generatedvalue;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/14/2024
 */
@Setter
@Getter
public class EmployeeId implements Serializable {
    private Long id;

    private int fieldId1;

    private int fieldId2;

    private int fieldId3;

    private int fieldId4;
}
