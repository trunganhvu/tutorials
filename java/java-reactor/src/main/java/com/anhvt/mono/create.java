/**
 * Copyright 2024
 * Name: create
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Creates a deferred emitter that can be used with callback-based APIs to signal at most one value,
 * a complete or an error signal.
 *
 * Consumer<MonoSink<String>> callback
 * sink.success(value)
 * sink.error(exception)
 * sink.success()
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class create {
    public static void main(String[] args) {
        Mono<String> mono = Mono.create(sink -> {
            try {
                String result = synchronousApiCall();      // mock action call api. Print synchronousApiCall
                sink.success("Hello, world!");          // success set value into onNext
                System.out.println("mono1 after success"); // print after onCompleted finished
            } catch (InterruptedException e) {
                sink.error(new RuntimeException("MyError"));
                throw new RuntimeException(e);
            }
        });

        mono.subscribe(
                value -> System.out.println("value: " + value),                 // value: Hello, world!
                error -> System.err.println("error: " + error.getMessage()),
                () -> System.out.println("completed")                           // completed
        );
        /**
         * Output:
         * synchronousApiCall
         * value: Hello, world!
         * completed
         * mono1 after success
         */

        Mono<String> mono1 = Mono.create(sink -> {
            try {
                String result = synchronousApiCall();      // mock action call api. Print synchronousApiCall
//                sink.success("Hello, world!");          // when success callback can't throw exceptions
                throw new InterruptedException("");
            } catch (InterruptedException e) {
                sink.error(new RuntimeException("MyError"));
                throw new RuntimeException(e);
            }
        });

        mono1.subscribe(
                value -> System.out.println("value: " + value),
                error -> System.err.println("error: " + error.getMessage()),    // error: MyError
                () -> System.out.println("completed")
        );
        /**
         * Output:
         * error: MyError
         */
    }

    static String synchronousApiCall() throws InterruptedException {
        System.out.println("synchronousApiCall");       // synchronousApiCall
        Thread.sleep(2000);
        return "synchronousApiCall";
    }
}
