/**
 * Copyright 2024
 * Name: Page
 */
package com.anhvt.springbootannotation.lombok.NonNull;

import lombok.NonNull;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class Page {
    public void println(@NonNull int id, @NonNull String name) {
        System.out.println("id: " + id + " - name: " + name);
    }
}
