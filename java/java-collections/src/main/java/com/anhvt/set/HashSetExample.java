/**
 * Copyright 2024
 * Name: HashSetExample
 */
package com.anhvt.set;

import java.util.HashSet;
import java.util.Set;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class HashSetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("C");
        set.add("A");
        set.add("B");
        set.add("C");

        System.out.println(set);    // [A, B, C]

        set.add("A1");
        System.out.println(set);    // [A1, A, B, C]

    }
}
