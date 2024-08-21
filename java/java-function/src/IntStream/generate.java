package IntStream;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Generate unlimited streams of the elements
 * @author AnhVT6
 *
 */
public class generate {
    public static void main(String[] args) {
        // Unlimited streams of element as 1
        Stream<Integer> infiniteStream = Stream.generate(() -> 1);
        infiniteStream
                .limit(10)		// set print limit 10 element
                .forEach(System.out::print);		// 1111111111

        System.out.println();
        
        // Unlimited streams of radom value elements
        IntStream infiniteIntStream = new Random().ints();
        infiniteIntStream
                .limit(10)		// set print limit 10 element
                .forEach(System.out::println);		// -1135466937 ...
    }
}
