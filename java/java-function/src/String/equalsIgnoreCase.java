package String;

public class equalsIgnoreCase {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "hello";
        String str3 = "World";
        String str4 = new String("hello");
        String str5 = null;

        System.out.println("str1 equals str2 (case-sensitive): " + str1.equals(str2));      // false
        System.out.println("str1 equalsIgnoreCase str2: " + str1.equalsIgnoreCase(str2));   // true
        System.out.println("str1 equalsIgnoreCase str3: " + str1.equalsIgnoreCase(str3));   // false
        System.out.println("str1 equalsIgnoreCase str4: " + str1.equalsIgnoreCase(str4));   // true
        System.out.println("str1 equalsIgnoreCase str5: " + str1.equalsIgnoreCase(str5));   // false
        System.out.println("str5 equalsIgnoreCase str1: " + str5.equalsIgnoreCase(str1));   // EXCEPTION: NullPointerException
    }
}
