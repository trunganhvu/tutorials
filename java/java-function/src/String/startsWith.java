package String;

public class startsWith {
    public static void main(String[] args) {
        String str = "Hello, world!";
        // Check start of string is input or not (case sensitive)
        System.out.println(str.startsWith("Hel")); // true
        System.out.println(str.startsWith("he")); // false



        // Check start of string is input or not (case sensitive) from index
        System.out.println(str.startsWith("world", 7)); // true
        System.out.println(str.startsWith("world", 4)); // false
    }
}
