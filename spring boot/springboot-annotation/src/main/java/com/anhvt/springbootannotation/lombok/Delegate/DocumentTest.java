/**
 * Copyright 2024
 * Name: DocumentTest
 */
package com.anhvt.springbootannotation.lombok.Delegate;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document();
        document.print("Hello, World!"); // This will call printer.print("Hello, World!")
    }
}
