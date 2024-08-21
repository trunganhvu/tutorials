/**
 * Copyright 2024
 * Name: Entity
 */
package com.anhvt.springbootannotation.lombok.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
@Setter
@Value
@AllArgsConstructor
@Getter
public class EntityData {
    private String name;
    private String title;
}
