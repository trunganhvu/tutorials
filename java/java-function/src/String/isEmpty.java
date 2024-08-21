package String;

public class isEmpty {
    public static void main(String[] args) {
        String str1 = "";           // true
        String str2 = "   ";        // false
        String str3 = "Hello";      // false

        System.out.println("str1 is empty: " + str1.isEmpty());     // true
        System.out.println("str2 is empty: " + str2.isEmpty());     // false
        System.out.println("str3 is empty: " + str3.isEmpty());     // false
    }
}
