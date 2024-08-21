package IntStream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * First element satisfy the condition then drop this
 * Every element can not satisfy the conditon then stop to return all remaining elements
 */
public class dropWhile {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 10);
        // Bỏ qua các số nguyên nhỏ hơn 5
        IntStream droppedStream = intStream.dropWhile(n -> n < 5);  // 5678910
        droppedStream.forEach(System.out::print);

        System.out.println();

        Stream.of(3,2,4,1,4,6,7,8,9,10)
                .dropWhile(i -> i > 5)  // 32414678910 - same with before
                .forEach(System.out::print);

        System.out.println();
        Stream.of(3,2,6,1,4,6,7,8,9,10)
                .dropWhile(i -> i < 5)  // 614678910
                .forEach(System.out::print);
    }
}
