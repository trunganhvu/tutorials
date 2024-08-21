/**
 * Copyright 2024
 * Name: Product
 */
package com.anhvt.springbootannotation.lombok.Data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
@Data
public class Product {
    private String name;
    private String color;
}
