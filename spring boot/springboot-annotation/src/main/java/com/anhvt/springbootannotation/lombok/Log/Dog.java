/**
 * Copyright 2024
 * Name: Dog
 */
package com.anhvt.springbootannotation.lombok.Log;

import lombok.extern.java.Log;

import java.util.logging.Level;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
@Log
public class Dog {
    public void printlnDog() {
        log.info("info dog");   // Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog INFO: info dog

        log.fine("fine dog");   // no print
        log.severe("servere dog"); // Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog SEVERE: servere dog

        log.log(Level.WARNING, "finish");   // Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog WARNING: finish
    }
}
