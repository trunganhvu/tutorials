/**
 * Copyright 2024
 * Name: PrinterImpl
 */
package com.anhvt.springbootannotation.lombok.Delegate;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
public class PrinterImpl implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
