/**
 * Copyright 2024
 * Name: Phone
 */
package com.anhvt.springbootannotation.lombok.ToString;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
@Getter
@Setter
@ToString(doNotUseGetters = false)
public class Phone extends Device{
    private int id = 1;
    private String name = "iphone";
}
