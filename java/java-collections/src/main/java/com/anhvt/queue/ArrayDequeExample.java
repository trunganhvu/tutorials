/**
 * Copyright 2024
 * Name: QueueExample
 */
package com.anhvt.queue;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class ArrayDequeExample {
    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        // Thêm phần tử vào đầu và cuối
        arrayDeque.addFirst(10);
        arrayDeque.addLast(20);
        arrayDeque.addFirst(5);
        arrayDeque.addLast(25);

        // In ra các phần tử
        System.out.println("ArrayDeque: " + arrayDeque);    // ArrayDeque: [5, 10, 20, 25]

        // Xóa và lấy phần tử đầu tiên
        System.out.println("Removed first: " + arrayDeque.removeFirst());

        // Xóa và lấy phần tử cuối cùng
        System.out.println("Removed last: " + arrayDeque.removeLast());

        // Kích thước của ArrayDeque
        System.out.println("Size: " + arrayDeque.size());

        // Kiểm tra có rỗng hay không
        System.out.println("Is empty: " + arrayDeque.isEmpty());
    }
}
