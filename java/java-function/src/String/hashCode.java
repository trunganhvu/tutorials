package String;

public class hashCode {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "World";
        String str3 = new String("World");

        // Hash data in string
        System.out.println("HashCode of str1: " + str1.hashCode());     // 69609650
        System.out.println("HashCode of str2: " + str2.hashCode());     // 83766130
        System.out.println("HashCode of str2: " + str3.hashCode());     // 83766130
    }
}
