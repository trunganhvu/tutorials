package com.anhvt;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class Main {
    public static void main(String[] args) {
        BD();
        BI();
    }

    static void BD() {

        BigDecimal X = new BigDecimal("0.03");
        BigDecimal Y = new BigDecimal("0.04");
        BigDecimal ans = Y.subtract(X);
        System.out.println(ans);

        BigDecimal a = new BigDecimal("456576345675.4546376");
        BigDecimal b = new BigDecimal("547634565352.986785764");

        // operations
        System.out.println(b.add(a));
        System.out.println(b.subtract(a));
        System.out.println(b.multiply(a));
        System.out.println(b.pow(2));
        System.out.println(b.negate());

        // constants
        BigDecimal con = BigDecimal.ONE;
    }


    static void BI() {
        int a = 30;
        int b = 67;

        BigInteger B = BigInteger.valueOf(6);
        int c = B.intValue();
        BigInteger C = new BigInteger("23456786837638976543256789");
        BigInteger X = new BigInteger("45367911115432");

        // constants
        BigInteger D = BigInteger.TEN;

        // operations
        BigInteger s = C.add(X);

        BigInteger m = C.multiply(X);

        BigInteger sub = C.subtract(X);

        BigInteger d = C.divide(X);

        BigInteger rem = C.remainder(X);

        if (X.compareTo(C) < 0) {
            System.out.println("Yes");
        }
    }
}