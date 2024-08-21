/**
 * Copyright 2024
 * Name: CouterTest
 */
package com.anhvt.springbootannotation.lombok.Synchronized;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class CouterTest {
    public static void main(String[] args) {
        testThread1();  // Index: 0 ... 70 100 ... 199 71 ... 99
//        testThread2();  // Index: 0 ... 62 100 ... 199 63 ... 99
    }

    private static void testThread1() {
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment1(i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                counter.increment1(i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testThread2() {
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment2(i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                counter.increment2(i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
