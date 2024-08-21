/**
 * Copyright 2024
 * Name: Book
 */
package com.anhvt.springbootannotation.lombok.ArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String name;
    private String author;
}
