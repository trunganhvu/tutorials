/**
 * Copyright 2024
 * Name: Car
 */
package com.anhvt.springbootannotation.lombok.Builder;

import lombok.Builder;
import lombok.Getter;

/**
 * Builder is a pattern to set values into fields
 *
 * @author trunganhvu
 * @date 8/9/2024
 */
@Builder
@Getter
public class Car {
    private String name;
    private String color;
}
