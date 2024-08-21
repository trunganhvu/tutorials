package String;

import java.util.stream.IntStream;

public class codePoints {
    public static void main(String[] args) {
        String str = "Hello 😊";

        // Get all code point as a IntStream
        // Code point is integer of represent a unicode character
        IntStream codePointsStream = str.codePoints();
        codePointsStream.forEach(System.out::println);  // 72 101 108 108 111 32 128522
    }
}
