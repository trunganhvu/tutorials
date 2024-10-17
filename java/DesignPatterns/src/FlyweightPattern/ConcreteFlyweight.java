/**
 * Copyright 2024
 * Name: ConcreteFlyweight
 */
package FlyweightPattern;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
public class ConcreteFlyweight implements Flyweight {
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("Intrinsic State: " + intrinsicState + ", Extrinsic State: " + extrinsicState);
    }
}
