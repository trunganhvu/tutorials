package String;

public class indexOf {
    public static void main(String[] args) {
        String str = "Hello, world!";
        int index = str.indexOf('o'); // Find index position of 'o' character
        System.out.println(index);  // 4


        String str1 = "Hello, world!";
        int index1 = str1.indexOf('o', 5); // Find index position of 'o' character from index 5
        System.out.println(index1); // 8


        String str2 = "Hello, world!";
        int index2 = str2.indexOf("world"); // Find start position of "world"
        System.out.println(index2); // 7


        // VD4:
        String str3 = "Hello, world!";
        int index3 = str3.indexOf("world", 7); // Find start position of "world" from index 7
        System.out.println(index3); // 7

    }
}
