package IntStream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Like design pattern Builder to build object for stream
 */
public class builder {
    public static void main(String[] args) {
        // Ex1
        IntStream.Builder builder = IntStream.builder();    // using IntStream builder()
        builder.add(1);
        builder.add(2);
        builder.add(3);
        builder.add(4);
        IntStream intStream1 = builder.build(); // Build interface Builder to IntStream
        intStream1.forEach(System.out::print);  // 1234


        System.out.println();

        // Ex2
        Stream.Builder<Integer> streamBuilder1 = Stream.builder();  // using Stream builder()
        streamBuilder1.add(5);
        streamBuilder1.add(6);
        streamBuilder1.add(7);
        streamBuilder1.add(8);
        IntStream intStream2 = streamBuilder1
                .build()        // Build interface Builder to IntStream
                .mapToInt(Integer::intValue);   // Map value in stream to integer
        intStream2.forEach(System.out::print);  // 5678

    }
}
