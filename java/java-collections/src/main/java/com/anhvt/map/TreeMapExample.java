/**
 * Copyright 2024
 * Name: TreeMapExample
 */
package com.anhvt.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/11/2024
 */
public class TreeMapExample {
    public static void main(String[] args) {
        // Tạo một TreeMap
        Map<String, Integer> map = new TreeMap<>();

        // Thêm các phần tử vào TreeMap
        map.put("Alice", 30);
        map.put("Bob", 25);
        map.put("Charlie", 30);
        map.put("Charlie", 35);

        // In ra kích thước của TreeMap
        System.out.println("Size of map: " + map.size());       // Size of map: 3

        // Kiểm tra xem một khóa có tồn tại không
        System.out.println("Contains key 'Alice': " + map.containsKey("Alice"));    // Contains key 'Alice': true
        System.out.println("Contains key 'David': " + map.containsKey("David"));    // Contains key 'David': false

        // Kiểm tra xem một giá trị có tồn tại không
        System.out.println("Contains value 25: " + map.containsValue(25));          // Contains value 25: true

        // Lấy giá trị theo khóa
        System.out.println("Value for key 'Bob': " + map.get("Bob"));               // Value for key 'Bob': 25
        System.out.println("Value for key 'Charlie': " + map.get("Charlie"));               // Value for key 'Bob': 25

        // Xóa một phần tử
        map.remove("Charlie");
        System.out.println("After removing 'Charlie': " + map);                     // After removing 'Charlie': {Alice=30, Bob=25}

        // Duyệt qua tất cả các khóa
        System.out.println("Keys in the map:");
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        // Duyệt qua tất cả các giá trị
        System.out.println("Values in the map:");
        for (Integer value : map.values()) {
            System.out.println(value);
        }

        // Duyệt qua tất cả các mục (key-value pairs)
        System.out.println("Key-Value pairs in the map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());       // Bob: 25, Alice: 30
        }
        //-----------------------------------------

        // Sử dụng merge
        map.merge("Alice", 5, Integer::sum);
        map.compute("Bob", (key, value) -> (value == null) ? 1 : value + 10);

        System.out.println("Key-Value pairs in the map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());       // Bob: 35, Alice: 35
        }
        //-----------------------------------------

        // computeIfAbsent
        map.computeIfAbsent("Alice", key -> 100);  // Alice đã có, không thay đổi
        map.computeIfAbsent("David", key -> 20);   // David không có, thêm vào với giá trị 20
        map.computeIfAbsent("Eve", key -> map.size() + 1); // Eve không có, thêm vào với giá trị 4 (kích thước hiện tại + 1)

        System.out.println("Key-Value pairs in the map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());       // Bob: 35, Eve: 4, Alice: 35, David: 20
        }
        //-----------------------------------------

        // computeIfPresent
        map.computeIfPresent("Alice", (key, value) -> value + 10);      // Alice: 35 + 10 = 45
        map.computeIfPresent("Bob", (key, value) -> null);              // Bob: xóa Bob khỏi map
        map.computeIfPresent("David", (key, value) -> value + 5);       // David: 20 + 5 = 25

        System.out.println("Key-Value pairs in the map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());       // Eve: 4, Alice: 45, David: 25
        }
        //-----------------------------------------

        // Get value with default
        System.out.println(map.getOrDefault("David", 0));       // 25

        // Put if key not exist
        map.putIfAbsent("David", 10);
        System.out.println("Key-Value pairs in the map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());       // Eve: 4, Alice: 45, David: 25
        }

        map.values().forEach(System.out::println);                              // 4 45 25

        System.out.println("Key-Value pairs in the map:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());       // Eve: 4, Alice: 45, David: 25
        }

        // Xóa tất cả các phần tử
        map.clear();
        System.out.println("After clearing the map: " + map);
    }
}
