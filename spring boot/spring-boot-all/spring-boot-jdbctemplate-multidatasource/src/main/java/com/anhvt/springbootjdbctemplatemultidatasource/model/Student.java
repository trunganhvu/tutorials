/**
 * Copyright 2024
 * Name: jdbc
 */
package com.anhvt.springbootjdbctemplatemultidatasource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = -339516038496531943L;
    private String sno;
    private String name;
    private String sex;
}
