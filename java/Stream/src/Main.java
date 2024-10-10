import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        sum();
        sumEvenNumber();
        findLongestString();
        upperCaseString();
        uniqueValue();
        sortNumber();
        sortObject();
    }

    static void sum() {
        List<Integer> list = Arrays.asList(1,2,3,6,7,1,6,2);
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum = " + sum);
    }

    static void sumEvenNumber() {
        List<Integer> list = Arrays.asList(1,2,3,6,7,1,6,2);
        int sum = list.stream()
                .filter(x -> x % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum = " + sum);
    }

    static void findLongestString() {
        List<String> list = Arrays.asList("asdgf","ddddddddd", "oaooaoaoa", "pppp");

        String result = String.valueOf(list.stream().max((s1, s2) -> {
//            System.out.println(s1 + " " + s2);
            return s1.length() - s2.length();
        }));
        System.out.println("result = " + result);
    }

    static void upperCaseString() {
        List<String> list = Arrays.asList("asdgf","ddddddddd", "oaooaoaoa", "pppp");

        List<String> newList = list.stream().map(String::toUpperCase).toList();
        List<String> newList2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(newList);
        System.out.println(newList2);
    }

    static void uniqueValue() {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6, 7, 7, 1, 8, 8, 9, 10);
        List<Integer> uniqueNumber = numbers.stream().distinct().toList();

        System.out.println(uniqueNumber);
    }

    static void sortNumber() {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 9, 2, 6, 7, 4, 1);

        List<Integer> sorted = numbers.stream().sorted().toList();

        System.out.println(sorted);
    }
    static void sortObject() {
        List<User> users = Arrays.asList(new User(4, "aa"),
                new User(1, "0"),
                new User(2, "ss"));

        List<User> sortedUsersID = users.stream().sorted(Comparator.comparing(User::id)).toList();
        List<User> sortedUsersName = users.stream().sorted(Comparator.comparing(User::name)).toList();
        System.out.println(sortedUsersID);
        System.out.println(sortedUsersName);
    }

    record User(int id, String name) {

    }
}