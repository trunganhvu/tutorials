/**
 * Copyright 2024
 * Name: onErrorMap
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Transform an error emitted by this Mono by synchronously applying a function to it if the error matches the given type.
 * Map exception which emit from Mono to other exception
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class onErrorMap {
    public static void main(String[] args) {

        // Mono phát ra một lỗi, và bạn muốn chuyển đổi(map) lỗi này -> thành một lỗi khác có ý nghĩa hơn
        // 1
        Mono.error(new ArithmeticException("Divide by zero"))
                .onErrorMap(ArithmeticException.class, ex -> new RuntimeException("ArithmeticException occurred"))  // this
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error.getMessage()) // Error: ArithmeticException occurred
                );
        // 2
        Mono.error(new IllegalArgumentException("Invalid argument"))
                .onErrorMap(ex -> new RuntimeException("Error occurred"))   // this
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error.getMessage()) // Error: Error occurred
                );
        // 3
        Mono.error(new IllegalArgumentException("Invalid argument"))
                .onErrorMap(e -> e instanceof IllegalArgumentException, e -> {
                    if (e.getMessage().length() < 10){
                        throw new RuntimeException("IllegalArgumentException occurred");
                    } else {
                        throw new RuntimeException("IllegalArgumentException occurred length <= 10");
                    }
                })
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error.getMessage()) // Error: IllegalArgumentException occurred length <= 10
                );
    }
}
