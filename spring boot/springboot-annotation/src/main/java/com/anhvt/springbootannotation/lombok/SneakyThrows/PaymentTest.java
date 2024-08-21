/**
 * Copyright 2024
 * Name: PaymentTest
 */
package com.anhvt.springbootannotation.lombok.SneakyThrows;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class PaymentTest {
    public static void main(String[] args) {
        Payment payment = new Payment();
        try {
            payment.bankPayment();  // bankPayment is running
                                    // Run code: throw new IOException("bankPayment throw exception");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());    //bankPayment throw exception
        }
        try {
            payment.paypalPayment();    // bankPayment is running
                                        // throw new IOException("paypalPayment throw exception");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());    // paypalPayment throw exception
        }
    }
}
