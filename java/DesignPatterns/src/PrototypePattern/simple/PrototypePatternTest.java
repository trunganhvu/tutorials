/**
 * Copyright 2024
 * Name: PrototypePatternTest
 */
package PrototypePattern.simple;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
public class PrototypePatternTest {
    public static void main(String[] args) {
        ConcretePrototype prototype = new ConcretePrototype("Original");
        ConcretePrototype clone = (ConcretePrototype) prototype.clone();
        System.out.println(clone.getName()); // In ra "Original"
    }
}

