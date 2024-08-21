/**
 * Copyright 2024
 * Name: EntityDataTest
 */
package com.anhvt.springbootannotation.lombok.Value;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class EntityDataTest {
    public static void main(String[] args) {
        EntityData entityData = new EntityData("aa", "bbb");
        System.out.println(entityData.getName());   // aa
//        entityData.setName("bb") // setter not found
    }
}
