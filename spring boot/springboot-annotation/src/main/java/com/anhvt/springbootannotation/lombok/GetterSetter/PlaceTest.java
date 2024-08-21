/**
 * Copyright 2024
 * Name: PlaceTest
 */
package com.anhvt.springbootannotation.lombok.GetterSetter;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
public class PlaceTest {
    public static void main(String[] args) {
        Place p1 = new Place();
        p1.setId("1");
        p1.setName("name1");
        p1.setAddress("11");
        System.out.println(p1.getId());     // 1
        System.out.println(p1.getName());   // name1
        System.out.println(p1.getAddress());// 11

    }
}
