/**
 * Copyright 2024
 * Name: ArrayBlockingQueue
 */
package com.anhvt.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class ArrayBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        // Tạo một thread producer
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Tạo một thread consumer
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    int value = queue.take();
                    System.out.println("Consumed: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Khởi động các thread
        producer.start();
        consumer.start();

        // Chờ các thread hoàn thành
        producer.join();
        consumer.join();

        /**
         * Output:
         * Produced: 0
         * Consumed: 0
         * Produced: 1
         * Consumed: 1
         * Produced: 2
         * Produced: 3
         * Produced: 4
         * Consumed: 2
         * Consumed: 3
         * Consumed: 4
         */
    }
}
