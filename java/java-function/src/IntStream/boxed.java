package IntStream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Convert IntStream to Stream<Integer>
 *
 * Return a Stream<Integer>
 */
public class boxed {
    public static void main(String[] args) {
        // IntStream ís simple traversal
        // Convert IntStream to List<Integer>
        List<Integer> integerList1 = IntStream.rangeClosed(1, 10)
                .boxed() // convert Stream<Integer>
                .toList();  // convert to List

        System.out.println("List integer: " + integerList1);    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

        // Convert IntStream to List<Integer>
        Set<Integer> integerSet1 = IntStream.rangeClosed(1, 10)
                .boxed() // convert Stream<Integer>
                .collect(Collectors.toSet());   // convert to Set

        System.out.println("List integer: " + integerSet1);     // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    }
}
