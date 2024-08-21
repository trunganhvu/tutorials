/**
 * Copyright 2024
 * Name: PageTest
 */
package com.anhvt.springbootannotation.lombok.NonNull;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class PageTest {
    public static void main(String[] args) {
        Page page = new Page();
        page.println(1, "11");  // id: 1 - name: 11
        page.println(2, null);  // NullPointerException: name is marked non-null but is null
    }
}
