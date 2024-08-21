/**
 * Copyright 2024
 * Name: Item
 */
package com.anhvt.springbootannotation.lombok.SuperBuilder;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Animal is a parent class using SuperBuilder
 *
 * @author trunganhvu
 * @date 8/9/2024
 */
@Getter
@SuperBuilder
public class Animal {
    private String name;
}
