/**
 * Copyright 2024
 * Name: Stazk
 */
package com.anhvt.list;

import java.util.Iterator;
import java.util.Stack;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/10/2024
 */
public class Stazk {
    private static void printStackType1(Stack<Integer> stackNumber) {
        for (int i = 0; i < stackNumber.size(); i++) {
            System.out.print(stackNumber.get(i));    // 19532
        }
    }

    private static void printStackType2(Stack<Integer> stackNumber) {
        for (int i : stackNumber) {
            System.out.print(i);                    // 19532
        }
    }

    private static void printStackType3(Stack<Integer> stackNumber) {
        Iterator<Integer> itr = stackNumber.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stackNumber = new Stack<>();

        // add element
        stackNumber.push(1);
        stackNumber.push(9);
        stackNumber.push(5);
        stackNumber.push(3);
        stackNumber.push(2);
        stackNumber.push(5);

        stackNumber.pop();
        printStackType1(stackNumber);     // 195325
        System.out.println();

        // Remove element (pop the top)
        stackNumber.pop(); // Remove the last added element (5)
        printStackType2(stackNumber);     // 19532
        System.out.println();

        // Size of stack
        System.out.println(stackNumber.size());      // 5

        // Get value by index
        System.out.println(stackNumber.get(3));      // 2

        // Check stack not empty
        if (!stackNumber.isEmpty()) {
            // Check exist element
            if (stackNumber.contains(5)) {
                System.out.println("Contain element 5");        // Contain element 5
            } else {
                System.out.println("Not contain element 5");
            }

            // containsAll() not applicable directly for Stack
            if (stackNumber.containsAll(java.util.List.of(1, 5))) {
                System.out.println("Contain element 1, 5");
            } else {
                System.out.println("Not contain element 1, 5"); // Not contain element 1, 5
            }
        }

        // Get index of element (not directly supported in Stack)
        System.out.println(stackNumber.search(5)); // Returns the position from the top (1-indexed)

        // Add element (push)
        stackNumber.push(5);
        System.out.println(stackNumber.search(5)); // 1

        // Retain common element (not directly applicable to Stack)
        // We will create a temporary List to demonstrate retainAll behavior
        java.util.List<Integer> tempList = java.util.List.of(5, 9);
        System.out.println(stackNumber.retainAll(tempList)); // true
        printStackType3(stackNumber);     // 55
        System.out.println(stackNumber.retainAll(java.util.List.of(5, 9, 9))); // false
        printStackType3(stackNumber);     // 55
        System.out.println();

        // Remove elements using condition
        stackNumber.removeIf(integer -> integer % 2 == 0);
        printStackType2(stackNumber);     // 5

        // Add null value
        stackNumber.push(null);
        printStackType3(stackNumber);     // 5null
        System.out.println();

        // Set value element into index position (index in range)
        stackNumber.set(0, 100);
        printStackType3(stackNumber);     // 100null
        System.out.println();

        stackNumber.sort(Integer::compareTo);
        System.out.println("end");
    }
}
