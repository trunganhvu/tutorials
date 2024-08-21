/**
 * Copyright 2024
 * Name: ProjectTest
 */
package com.anhvt.springbootannotation.lombok.EqualsAndHashCode;

/**
 * Test object project using EqualsAndHashCode
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
public class ProjectTest {
    public static void main(String[] args) {
        Project project1 = new Project();
        project1.setName("p1");
        project1.setTitle("t1");

        System.out.println(project1);                   // com.anhvt.springbootannotation.lombok.EqualsAndHashCode.Project@34751
        System.out.println(project1.hashCode());        // 214865
        System.out.println(project1.equals(new Project())); // false
    }
}
