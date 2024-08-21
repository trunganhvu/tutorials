/**
 * Copyright 2024
 * Name: Payment
 */
package com.anhvt.springbootannotation.lombok.SneakyThrows;

import lombok.SneakyThrows;

import java.io.IOException;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class Payment {

    @SneakyThrows
    public void bankPayment() {
        System.out.println("bankPayment is running");
        throw new IOException("bankPayment throw exception");    // Checked exception
    }

    public void paypalPayment() throws IOException {
        System.out.println("bankPayment is running");
        throw new IOException("paypalPayment throw exception");    // Checked exception
    }
}
