/**
 * Copyright 2024
 * Name: fromSupplier
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Create a Mono, producing its value using the provided Supplier.
 * Runnable return value and handler actions so Mono can emit
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class fromSupplier {
    public static void main(String[] args) {
        // Tạo một Supplier để sinh ra một số ngẫu nhiên từ 1 đến 100
        Supplier<Integer> randomSupplier = () -> randomFunc();

        // Tạo một Mono từ Supplier
        Mono<Integer> randomMono = Mono.fromSupplier(randomSupplier);

        // Subscribe để nhận giá trị từ Mono
        randomMono.subscribe(
                value -> System.out.println("Value: " + value),     // Value: 54
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")               // Completed
        );
        /**
         * Output:
         * randomFunc
         * Value: 54
         * Completed
         */
    }

    static int randomFunc() {
        Random random = new Random();
        System.out.println("randomFunc");
        return random.nextInt(100) + 1;
    }
}
