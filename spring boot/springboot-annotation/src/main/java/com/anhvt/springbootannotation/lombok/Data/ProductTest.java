/**
 * Copyright 2024
 * Name: ProductTest
 */
package com.anhvt.springbootannotation.lombok.Data;

/**
 * Test object product using @Data
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
public class ProductTest {
    public static void main(String[] args) {
        Product product1 = new Product();
        product1.setName("p1");
        product1.setColor("c1");
        System.out.println(product1);                   // Product(name=p1, color=c1)
        System.out.println(product1.toString());        // Product(name=p1, color=c1)

        System.out.println(product1.hashCode());        // 214338
        System.out.println(product1.equals(new Product())); //false
    }
}
