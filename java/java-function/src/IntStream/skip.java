package IntStream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Skip N elements first of stream and do something with remaining element
 * @author AnhVT6
 *
 */
public class skip {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.iterate(1, n -> n + 1)
                .limit(10);
        stream.skip(3)		// skip 3 elements first (1,2,3)
        		.forEach(System.out::print);	// 45678910


        System.out.println();
        
        IntStream intStream = IntStream.rangeClosed(1, 10);
        intStream.skip(3)	// skip 3 elements first (1,2,3)
                .forEach(System.out::print);	// 45678910
    }
}
