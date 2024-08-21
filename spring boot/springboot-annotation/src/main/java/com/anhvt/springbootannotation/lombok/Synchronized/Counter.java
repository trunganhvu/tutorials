/**
 * Copyright 2024
 * Name: Couter
 */
package com.anhvt.springbootannotation.lombok.Synchronized;

import lombok.Synchronized;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class Counter {
    private int count = 0;

    public synchronized void increment1(int index) {
        count++;
        System.out.println("Index: " + index);
    }

    @Synchronized
    public void increment2(int index) {
        count++;
        System.out.println("Index: " + index);
    }

    public int getCount() {
        return count;
    }
}
