/**
 * Copyright 2024
 * Name: delay
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Delay each of this Flux elements (Subscriber.onNext(T) signals) by a given Duration.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class delayElements {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("Hello").delayElements(Duration.ofSeconds(1));
        flux1.subscribe(System.out::println);    // Hello

        try {
            Thread.sleep(4000);
            System.out.println("Done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
