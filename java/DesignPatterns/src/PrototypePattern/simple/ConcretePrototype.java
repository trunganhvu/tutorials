/**
 * Copyright 2024
 * Name: ConcretePrototype
 */
package PrototypePattern.simple;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
public class ConcretePrototype implements Prototype {
    private String name;

    public String getName() {
        return name;
    }

    public ConcretePrototype(String name) {
        this.name = name;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.name);
    }
}

