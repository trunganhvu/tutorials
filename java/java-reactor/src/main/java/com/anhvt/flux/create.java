/**
 * Copyright 2024
 * Name: create
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Programmatically create a Flux with the capability of emitting multiple elements
 * in a synchronous or asynchronous manner through the FluxSink API.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class create {
    public static void main(String[] args) {
        Flux<String> flux = Flux.create(sink -> {
            sink.next("Hello");
            sink.next("World");
            sink.complete();
        });

        flux.subscribe(
                value -> System.out.println("value: " + value),                 // value: Hello world!
                error -> System.err.println("error: " + error.getMessage()),
                () -> System.out.println("completed")                           // completed
        );

        Flux<String> flux1 = Flux.create(sink -> {
            sink.next("Hello");
            sink.next("World");
            sink.error(new RuntimeException("Errorr"));
            sink.complete();
        });

        flux1.subscribe(
                value -> System.out.println("value: " + value),                 // value: Hello world!
                error -> System.err.println("error: " + error.getMessage()),    // error: Errorr
                () -> System.out.println("completed")
        );
    }
}
