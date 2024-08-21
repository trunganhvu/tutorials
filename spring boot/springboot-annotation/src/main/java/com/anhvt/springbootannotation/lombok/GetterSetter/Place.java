/**
 * Copyright 2024
 * Name: Place
 */
package com.anhvt.springbootannotation.lombok.GetterSetter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
@Setter
@Getter
public class Place {
    private String id;
    private String name;

    @Setter(AccessLevel.PROTECTED)  // access modify of setter is protected for this field
    private String address;
}
