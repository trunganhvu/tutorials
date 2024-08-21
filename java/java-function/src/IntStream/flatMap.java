package IntStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Convert stream include sub stream (or collection) to stream
 * @author AnhVT6
 *
 */
public class flatMap {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hello", "World");

        // flatMap to get word in list add in a stream
        Stream<Character> characters = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c));
        
        characters.forEach(System.out::print);	// HelloWorld
        

        System.out.println("\n========================");


        List<int[]> integerArrays = Arrays.asList(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9});
        IntStream integerStream = integerArrays.stream()
                .flatMapToInt(Arrays::stream);
        integerStream.forEach(System.out::print);	// 123456789
    }
}
