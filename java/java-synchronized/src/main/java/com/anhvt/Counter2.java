/**
 * Copyright 2024
 * Name: Counter1
 */
package com.anhvt;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class Counter2 {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
