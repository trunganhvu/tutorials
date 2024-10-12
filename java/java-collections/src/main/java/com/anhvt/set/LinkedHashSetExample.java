/**
 * Copyright 2024
 * Name: LinkedHashSetExample
 */
package com.anhvt.set;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class LinkedHashSetExample {
    public static void main(String[] args) {
        Set<Integer> linkedHashSet = new LinkedHashSet<>();

        // Thêm phần tử
        linkedHashSet.add(10);
        linkedHashSet.add(25);
        linkedHashSet.add(20);
        linkedHashSet.add(15);
        linkedHashSet.add(25);

        // In ra các phần tử
        System.out.println("LinkedHashSet: " + linkedHashSet);          // LinkedHashSet: [10, 25, 20, 15]

        // Kiểm tra phần tử
        System.out.println("Contains 15: " + linkedHashSet.contains(15));   // Contains 15: true

        // Xóa một phần tử
        linkedHashSet.remove(20);
        System.out.println("After removing 20: " + linkedHashSet);      // After removing 20: [10, 25, 15]

        // Kích thước của LinkedHashSet
        System.out.println("Size: " + linkedHashSet.size());        // Size: 3

        // Kiểm tra có rỗng hay không
        System.out.println("Is empty: " + linkedHashSet.isEmpty());     // Is empty: false
    }
}
