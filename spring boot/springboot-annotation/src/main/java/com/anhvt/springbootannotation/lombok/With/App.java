/**
 * Copyright 2024
 * Name: App
 */
package com.anhvt.springbootannotation.lombok.With;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
@With
@AllArgsConstructor
@ToString
public class App {
    private String name;
    private String tech;
}
