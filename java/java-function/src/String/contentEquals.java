package String;

public class contentEquals {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = "World";

        StringBuilder stringBuilder = new StringBuilder("Hello");

        if (str1 == str2) {
            System.out.println("str1 == str2");
        } else {
            System.out.println("str1 != str2");
        }

        boolean result1 = str1.contentEquals(str2);
        System.out.println("str1 content equals str2: " + result1); // true

        boolean result2 = str1.contentEquals(str3);
        System.out.println("str1 content equals str3: " + result2); // false

        boolean result3 = str1.contentEquals(stringBuilder);
        System.out.println("str1 content equals stringBuilder: " + result3);    // true



        // EX:
        String str = "Hello";
        StringBuffer stringBuffer1 = new StringBuffer("Hello");
        StringBuffer stringBuffer2 = new StringBuffer("World");

        boolean result5 = str.contentEquals(stringBuffer1);
        System.out.println("str content equals stringBuffer1: " + result5); // true

        boolean result6 = str.contentEquals(stringBuffer2);
        System.out.println("str content equals stringBuffer2: " + result6); // false
    }
}
