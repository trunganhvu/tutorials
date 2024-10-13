/**
 * Copyright 2024
 * Name: Counter4
 */
package com.anhvt;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class Counter4 {
    private static int count = 0;
    public static synchronized void increment() {
        count++;
    }

    public static synchronized int getCount() {
        return count;
    }
}
