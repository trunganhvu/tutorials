package String;

public class matches {
    public static void main(String[] args) {
        String str1 = "Hello123";
        String str2 = "123Hello1";

        boolean result1 = str1.matches("[a-zA-Z]+\\d+");
        boolean result2 = str2.matches("[a-zA-Z]+\\d+");
        System.out.println(result1); // true
        System.out.println(result2); // false
    }
}
