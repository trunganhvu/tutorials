/**
 * Copyright 2024
 * Name: GFG
 */
package com.anhvt.map.ConcurrentHashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/30/2024
 */
public class GFG {
    // Creating a static HashMap class object
    static HashMap m = new HashMap();

    public void run() {
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
        }
        System.out.println("Child Thread updating Map");
        m.put(103, "C");
    }

    public static void main(String arg[]) throws InterruptedException {
        m.put(101, "A");
        m.put(102, "B");
        // Creating thread inside main() method
        GFG t = new GFG();

        // Starting the thread
        t.run();
        Set s1 = m.keySet();

        Iterator itr = s1.iterator();
        while (itr.hasNext()) {
            Integer I1 = (Integer)itr.next();
            System.out.println("Main Thread Iterating Map and Current Entry is:"
                            + I1 + "..." + m.get(I1));
            Thread.sleep(3000);
        }
        System.out.println(m);

        /**
         * Output:
         * Child Thread updating Map
         * Main Thread Iterating Map and Current Entry is:101...A
         * Main Thread Iterating Map and Current Entry is:102...B
         * Main Thread Iterating Map and Current Entry is:103...C
         * {101=A, 102=B, 103=C}
         */
    }
}
