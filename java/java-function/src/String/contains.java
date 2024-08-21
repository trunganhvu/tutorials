package String;

public class contains {
    public static void main(String[] args) {
        String str = "Hello, World!";

        // Check string "Hello" exists in str
        boolean containsHello = str.contains("Hello");
        System.out.println("Contains 'Hello': " + containsHello);   // true

        // Check string "world" exists in str
        boolean containsWorld = str.contains("world");
        System.out.println("Contains 'world': " + containsWorld);   // false

        boolean containsABC = str.contains("abc");
        System.out.println("Contains 'abc': " + containsABC);   // false
    }
}
