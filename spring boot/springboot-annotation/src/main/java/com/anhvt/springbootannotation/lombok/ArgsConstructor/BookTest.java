/**
 * Copyright 2024
 * Name: BookTest
 */
package com.anhvt.springbootannotation.lombok.ArgsConstructor;

/**
 * Test class book using NoArgsConstructor and AllArgsConstructor
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
public class BookTest {
    public static void main(String[] args) {
        Book book1 = new Book();
        book1.setName("book1");
        book1.setAuthor("author1");
        System.out.println(book1.getName() + " " + book1.getAuthor());  // book1 author1

        Book book2 = new Book("book2", "author2");
        System.out.println(book2.getName() + " " + book2.getAuthor());  // book2 author2
    }
}
