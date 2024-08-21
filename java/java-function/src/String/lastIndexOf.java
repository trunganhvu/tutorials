package String;

public class lastIndexOf {
    public static void main(String[] args) {
        String str = "Hello, World! Hello";
        // Find last position by word
        System.out.println("Last position of 'Hello': " + str.lastIndexOf("Hello"));                // 14
        // Find last position by unicode number
        System.out.println("Last position of character which unicode=111: " + str.lastIndexOf(111));// 18
        // Find last position by word with range [0, 8]
        System.out.println("Last position of 'o' which start position 5: " + str.lastIndexOf('o', 8));  // 8
        // Find last position by unicode number with range [0, 8]
        System.out.println("Last position of 'o' which start position 5: " + str.lastIndexOf(111, 5));  //4
    }
}
