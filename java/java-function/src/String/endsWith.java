package String;

public class endsWith {
    public static void main(String[] args) {
        String str = "Hello, World";

        // Check end of string have exist other string
        boolean endsWithWorld = str.endsWith("World");
        System.out.println("Ends with 'World': " + endsWithWorld);  // true

        boolean endsWithWorldLowercase = str.endsWith("world");
        System.out.println("Ends with 'world' (case-sensitive): " + endsWithWorldLowercase);    // false
    }
}
