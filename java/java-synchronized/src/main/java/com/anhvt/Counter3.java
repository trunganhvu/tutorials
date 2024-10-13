/**
 * Copyright 2024
 * Name: Counter3
 */
package com.anhvt;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class Counter3 {
    private Object lock = new Object();

    private int count = 0;

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}
