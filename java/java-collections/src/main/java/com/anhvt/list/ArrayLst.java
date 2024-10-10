/**
 * Copyright 2024
 * Name: ArrayList
 */
package com.anhvt.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/10/2024
 */
public class ArrayLst {
    private static void printListType1(List<Integer> listNumber) {
        for (int i = 0; i < listNumber.size(); i++){
            System.out.print(listNumber.get(i));    // 19532
        }
    }

    private static void printListType2(List<Integer> listNumber) {
        for (int i: listNumber) {
            System.out.print(i);                    // 19532
        }
    }

    private static void printListType3(List<Integer> listNumber) {
        Iterator<Integer> itr = listNumber.iterator();
        while (itr.hasNext()){
            System.out.print(itr.next());
        }
    }

    public static void main(String[] args) {
        List<Integer> listNumber = new ArrayList<>();

        // add element
        listNumber.add(1);
        listNumber.add(9);
        listNumber.add(5);
        listNumber.add(3);
        listNumber.add(2);

        printListType1(listNumber);     // 19532
        System.out.println();

        // Remove element
        listNumber.remove(0);
        System.out.println();
        printListType2(listNumber);     // 9532
        System.out.println();

        // Size of list
        System.out.println(listNumber.size());      // 4

        // Get value by index
        System.out.println(listNumber.get(3));      // 2

        // Check list not empty
        if (!listNumber.isEmpty()) {
            // Check exist element
            if (listNumber.contains(5)) {
                System.out.println("Contain element 5");        // Contain element 5
            } else {
                System.out.println("Not contain element 5");
            }

            // containsAll() same with contains() && contains() &&...
            if (listNumber.containsAll(List.of(1,5))) {
                System.out.println("Contain element 1, 5");
            } else {
                System.out.println("Not contain element 1, 5");     // Not contain element 1, 5
            }
        }

        // Get index of element
        System.out.println(listNumber.indexOf(5)); // 1

        // Add element 5 into position 3
        listNumber.add(3, 5);
        System.out.println(listNumber.lastIndexOf(5)); // 3

        // retainAll get common element
        // return true if list changed
        // return false if list not changed
        printListType3(listNumber);     // 95352
        System.out.println(listNumber.retainAll(List.of(5, 9)));    // true
        printListType3(listNumber);     // 955
        System.out.println(listNumber.retainAll(List.of(5, 9, 9)));   // false
        printListType3(listNumber);     // 955
        System.out.println();

        listNumber.removeIf(integer -> integer % 2 == 0);
        listNumber.sort(Integer::compareTo);    // 559

        listNumber.replaceAll(integer -> integer * 2);


        printListType2(listNumber);     // 101018
        System.out.println();

        // Add null value
        listNumber.add(null);
        printListType3(listNumber);     // 101018null
        System.out.println();

        System.out.println("end");
    }
}
