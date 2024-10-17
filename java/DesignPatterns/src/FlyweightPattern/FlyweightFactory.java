/**
 * Copyright 2024
 * Name: FlyweightFactory
 */
package FlyweightPattern;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
import java.util.HashMap;

public class FlyweightFactory {
    private HashMap<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String intrinsicState) {
        return flyweights.merge(intrinsicState,
                new ConcreteFlyweight(intrinsicState),
                (existing, newFlyweight) -> existing);
    }
}
