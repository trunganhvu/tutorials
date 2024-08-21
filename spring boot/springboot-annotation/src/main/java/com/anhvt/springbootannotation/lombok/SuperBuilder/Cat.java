/**
 * Copyright 2024
 * Name: Bike
 */
package com.anhvt.springbootannotation.lombok.SuperBuilder;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/9/2024
 */
@Getter
@SuperBuilder
public class Cat extends Animal {
    private String action;
}
