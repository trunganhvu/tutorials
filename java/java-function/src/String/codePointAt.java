package String;

public class codePointAt {
    public static void main(String[] args) {
        String str = "Hello";

        // Get code point of character in string
        // Code point is integer of represent a unicode character
        int codePoint0 = str.codePointAt(0);
        int codePoint1 = str.codePointAt(1);
        int codePointLast = str.codePointAt(str.length() - 1);

        System.out.println("Code point at position 0: " + codePoint0);      // 72
        System.out.println("Code point at position 1: " + codePoint1);      // 101
        System.out.println("Code point at last position: " + codePointLast);//111


        System.out.println("Character at position 0: " + Character.toString(codePoint0));       // H
        System.out.println("Character at position 1: " + Character.toString(codePoint1));       // e
        System.out.println("Character at last position: " + Character.toString(codePointLast)); // o
    }
}
