/**
 * Copyright 2024
 * Name: CarTest
 */
package com.anhvt.springbootannotation.lombok.Builder;

/**
 * Test car object with builder
 *
 * @author trunganhvu
 * @date 8/9/2024
 */
public class CarTest {
    public static void main(String[] args) {
        Car car = Car.builder()     // Builder is a design pattern
                .name("item1")
                .color("black")
                .build();

        System.out.println(car.getName());      // item1
        System.out.println(car.getColor());     // black
    }
}
