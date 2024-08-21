package String;

public class isBlank {
    public static void main(String[] args) {
        String str1 = "";       // true
        String str2 = "   ";    // true
        String str3 = "Hello";  // false

        System.out.println("str1 is blank: " + str1.isBlank());     // true
        System.out.println("str2 is blank: " + str2.isBlank());     // true
        System.out.println("str3 is blank: " + str3.isBlank());     // false
    }
}
