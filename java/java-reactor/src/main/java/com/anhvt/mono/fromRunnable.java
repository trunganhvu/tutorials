/**
 * Copyright 2024
 * Name: fromRunnable
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Create a Mono that completes empty once the provided Runnable has been executed.
 * Runnable not return any value so Mono can not emit anythings
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class fromRunnable {
    public static void main(String[] args) {
        Runnable task = fromRunnable::saveData;

        Mono<Void> mono = Mono.fromRunnable(task);
        mono.subscribe(
                value -> System.out.println("Received: " + value),  // Runnable not have any value to emit
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")               // Completed
        );
        /**
         * Output:
         * Saving
         * Completed
         */
    }

    private static void saveData() {
        try {
            System.out.println("Saving");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
