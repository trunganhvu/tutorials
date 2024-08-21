package String;

public class equals {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = "World";
        String str4 = new String("Hello");
        String str5 = null;

        // Compares this string to the specified object.
        System.out.println("str1 equals str2: " + str1.equals(str2));   // true
        System.out.println("str1 equals str3: " + str1.equals(str3));   // false
        System.out.println("str1 equals str4: " + str1.equals(str4));   // true
        System.out.println("str1 equals str5: " + str1.equals(str5));   // false
        System.out.println("str5 equals str1: " + str5.equals(str1));   // EXCEPTION: NullPointerException
    }
}
