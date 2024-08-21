/**
 * Copyright 2024
 * Name: PersonTest
 */
package com.anhvt.springbootannotation.lombok.Accessors;

/**
 * Test person object with Accessors
 *
 * @author trunganhvu
 * @date 8/9/2024
 */
public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.name("name").age(1);
        person.address("address");
        System.out.println(person.name());      // name
    }
}
