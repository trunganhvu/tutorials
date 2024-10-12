/**
 * Copyright 2024
 * Name: BlockingDequeExample
 */
package com.anhvt.queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class BlockingDequeExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Integer> deque = new LinkedBlockingDeque<>();

        // Tạo một thread producer
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    deque.putLast(i); // Thêm vào cuối
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
                    int value = deque.takeFirst(); // Lấy từ đầu
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
         * Consumed: 0
         * Produced: 0
         * Produced: 1
         * Produced: 2
         * Produced: 3
         * Produced: 4
         * Consumed: 1
         * Consumed: 2
         * Consumed: 3
         * Consumed: 4
         */
    }
}
