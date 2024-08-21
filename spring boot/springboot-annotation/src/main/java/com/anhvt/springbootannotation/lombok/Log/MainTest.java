/**
 * Copyright 2024
 * Name: MainTest
 */
package com.anhvt.springbootannotation.lombok.Log;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class MainTest {
    public static void main(String[] args) {
        // ---------------CommonsLog---------------
        Cat cat1 = new Cat();
        cat1.println();

        /**
         * Output:
         * 14:47:40.061 [main] INFO com.anhvt.springbootannotation.lombok.Log.Cat -- start
         * 14:47:40.077 [main] ERROR com.anhvt.springbootannotation.lombok.Log.Cat -- finish
         */


        // ---------------Log---------------
        Dog dog1 = new Dog();
        dog1.printlnDog();
        /**
         * Output
         * Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog
         * INFO: info dog
         * Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog
         * SEVERE: servere dog
         * Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog
         * WARNING: finish
         */

        // ---------------Slf4j---------------
        Pig pig1 = new Pig();
        pig1.printlnPig();
        /**
         * Output:
         * 14:54:11.386 [main] INFO com.anhvt.springbootannotation.lombok.Log.Pig -- info pig
         * 14:54:11.387 [main] ERROR com.anhvt.springbootannotation.lombok.Log.Pig -- error pig
         * 14:54:11.387 [main] WARN com.anhvt.springbootannotation.lombok.Log.Pig -- warn pig
         */
    }
}
