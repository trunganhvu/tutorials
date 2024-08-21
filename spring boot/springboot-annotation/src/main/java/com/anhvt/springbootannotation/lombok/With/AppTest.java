/**
 * Copyright 2024
 * Name: AppTest
 */
package com.anhvt.springbootannotation.lombok.With;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class AppTest {
    public static void main(String[] args) {
        App app = new App("x", "java");
        System.out.println(app);        // App(name=x, tech=java)
        App app1 = app.withName("teams");
        System.out.println(app1);       // App(name=teams, tech=java)
    }
}
