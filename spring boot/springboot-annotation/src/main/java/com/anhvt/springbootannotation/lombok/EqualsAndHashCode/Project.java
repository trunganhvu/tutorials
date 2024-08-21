/**
 * Copyright 2024
 * Name: Project
 */
package com.anhvt.springbootannotation.lombok.EqualsAndHashCode;

import com.anhvt.springbootannotation.lombok.Data.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

/**
 * Project object using EqualsAndHashCode
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project {
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private String title;
    @EqualsAndHashCode.Include
    private int field1;
    @EqualsAndHashCode.Include
    private Date field2;
    @EqualsAndHashCode.Include
    private Instant field3;
}
