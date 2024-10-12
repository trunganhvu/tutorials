/**
 * Copyright 2024
 * Name: TreeSetExample
 */
package com.anhvt.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class TreeSetExample {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>();
        // Thêm phần tử
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(15);
        treeSet.add(25);

        // In ra các phần tử
        System.out.println("TreeSet: " + treeSet);              // [10, 15, 20, 25]

        // Kiểm tra phần tử
        System.out.println("Contains 15: " + treeSet.contains(15)); // Contains 15: true

        // Xóa một phần tử
        treeSet.remove(20);
        System.out.println("After removing 20: " + treeSet);    // After removing 20: [10, 15, 25]

        // Setup comparator
        Set<String> fruits = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        });
        // Adding new elements to a TreeSet
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Pineapple");
        fruits.add("Orange");

        System.out.println("Fruits Set : " + fruits);       // Fruits Set : [Pineapple, Orange, Banana, Apple]

        System.out.println(((TreeSet<String>) fruits).first()); // Pineapple
    }
}
