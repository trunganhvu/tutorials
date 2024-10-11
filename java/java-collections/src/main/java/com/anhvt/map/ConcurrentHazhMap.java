/**
 * Copyright 2024
 * Name: ConcurrentHazhMap
 */
package com.anhvt.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/11/2024
 */
public class ConcurrentHazhMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();

        // Thêm phần tử
        map.put("Alice", 30);
        map.put("Bob", 25);

        // Lấy giá trị
        System.out.println("Alice's age: " + map.get("Alice"));             // Alice's age: 30

        // Kiểm tra sự tồn tại
        System.out.println("Contains key 'Bob': " + map.containsKey("Bob")); // true

        // Xóa phần tử
        map.remove("Bob");

        // Duyệt qua các phần tử
        System.out.println("Key-Value pairs in the map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());   // Alice: 30,
        }

        // Kích thước của ConcurrentHashMap
        System.out.println("Size of map: " + map.size()); // 1
    }
}
