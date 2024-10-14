package com.anhvt;

import java.util.HashMap;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class Main {
    public static void main(String[] args) {
        // hashDemo();
        HashMapExample<String, String> map = new HashMapExample<>();

        map.put("Mango", "King of fruits");
        map.put("Apple", "A sweet red fruit");
        map.put("Litchi", "Kunal's fav fruit");

        System.out.println(map);
    }
}