package String;

import java.util.stream.Stream;

public class lines {
    public static void main(String[] args) {

        // Using Stream to mapping lines in string to Stream<String>
        String text = "Hello\nThis is a\nMulti-line\nString";
        Stream<String> linesStream = Stream.of(text)
                .flatMap(str -> Stream.of(str.split("\n")));
        linesStream.forEach(System.out::println);
        // Hello
        // This is a
        // ...


        // Using lines() to mapping
        String text1 = "Line 1\nLine 2\nLine 3\nLine 4";
        Stream<String> linesStream1 = text1.lines();
        linesStream1.forEach(System.out::println);
        // Line 1
        // Line 2
        // Line 3
        // Line 4

    }
}
