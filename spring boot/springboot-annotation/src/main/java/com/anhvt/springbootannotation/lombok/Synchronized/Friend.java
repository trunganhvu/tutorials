/**
 * Copyright 2024
 * Name: Friend
 */
package com.anhvt.springbootannotation.lombok.Synchronized;

import lombok.Synchronized;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class Friend {
    private final String objectToLock = new String("object friend");
    @Synchronized
    public static void sayHello() {
        System.out.println("Hello!");
    }

    @Synchronized
    public void getOne() {
        System.out.println("One");
    }

    @Synchronized("objectToLock")
    public void printObject() {
        System.out.println(objectToLock);
    }

    public synchronized void getTwo() {
        System.out.println("Two");
    }
}
