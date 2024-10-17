/**
 * Copyright 2024
 * Name: FlyweightPatternTest
 */
package FlyweightPattern;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
public class FlyweightPatternTest {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight("State1");
        Flyweight flyweight2 = factory.getFlyweight("State1");

        flyweight1.operation("First Call");     // Intrinsic State: State1, Extrinsic State: First Call
        flyweight2.operation("Second Call");    // Intrinsic State: State1, Extrinsic State: Second Call
    }
}
