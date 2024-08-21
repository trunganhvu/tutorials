/**
 * Copyright 2024
 * Name: CatTest
 */
package com.anhvt.springbootannotation.lombok.SuperBuilder;

/**
 * Test cat object with SuperBuilder
 *
 * @author trunganhvu
 * @date 8/9/2024
 */
public class CatTest {
    public static void main(String[] args) {
        Cat cat = Cat.builder()  // Create object with builder
                .name("Cat1")       // Set field in parent object using @SuperBuilder
                .action("cat action")// Set field in current child using @SuperBuilder
                .build();       // final to created new object

        System.out.println(cat.getName());      // Cat1
        System.out.println(cat.getAction());    // cat action
    }
}
