package String;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class chars {
    public static void main(String[] args) {
        String str = "hello world";

        // IntStream ís simple traversal => Created 2 variables
        IntStream charsStream1 = str.chars();
        charsStream1.forEach(System.out::print); // 104 101 108 108 111 ... ~ h e l l o ...


        long count = str.chars().count(); // Exception when str is NULL. str is BLANK will be return 0

        IntStream charsStream2 = str.chars();
        long count2 = charsStream2.count();  //  Exception when str is BLANK
        System.out.println("Number of characters1: " + count); // 11
        System.out.println("Number of characters2: " + count2);// 11


        // EX: Upper case
        String upperCaseStr = "hello world".chars()
                .mapToObj(c -> (char) c) // Convert int value in Stream to Character.
                .map(Character::toUpperCase) // Uppercase Character
                .map(String::valueOf) // Convert to String
                .collect(Collectors.joining()); // Merge all char type String into

        System.out.println("Upper case string: " + upperCaseStr);


        // VD3:
        String onlyLetters = "hello 123 world!".chars()
                .filter(Character::isLetter) // Filter only letter, have not number
                .mapToObj(c -> (char) c) // Convert int value in Stream to Character.
                .map(String::valueOf) // Convert to String
                .collect(Collectors.joining());

        System.out.println("Only letters: " + onlyLetters);
    }
}
