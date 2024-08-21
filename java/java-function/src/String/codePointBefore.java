package String;

public class codePointBefore {
    public static void main(String[] args) {
        String str = "Hello";

        // Get code point of pre character in string (index = 1 -> pre index = 0)
        // Code point is integer of represent a unicode character
        int codePoint1 = str.codePointBefore(1);
        System.out.println("Code point before position 1: " + codePoint1 + " ~ " + Character.toString(codePoint1));  // 72 ~ H

        int codePoint3 = str.codePointBefore(3);
        System.out.println("Code point before position 3: " + codePoint3 + " ~ " + Character.toString(codePoint3));  // 108 ~ l

        int codePoint0 = str.codePointBefore(0); // Exception: StringIndexOutOfBoundsException - String index out of range: 0

    }
}
