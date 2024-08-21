package String;

public class copyValueOf {
    public static void main(String[] args) {
        char[] data = {'H', 'e', 'l', 'l', 'o'};
        String str = String.copyValueOf(data); // copy is create new string object
        System.out.println("String created from char array: " + str); // Hello


        // EX:
        char[] data1 = {'H', 'e', 'l', 'l', 'o'};
        String newString = new String(data1); // The same with copyValueOf
        System.out.println("String created from char array: " + newString); // Hello


        // EX:
        char[] data2 = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};
        String str2 = String.copyValueOf(data2, 6, 5);
        System.out.println("String created from a part of char array: " + str2);    // World
    }
}
