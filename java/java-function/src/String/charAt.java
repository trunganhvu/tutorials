package String;

public class charAt {
    public static void main(String[] args) {
        String str = "Hello, World!";
        System.out.println("Character at index 0: " + str.charAt(0));                   // H
        System.out.println("Character at index 7: " + str.charAt(7));                   // W
        System.out.println("Character at last index: " + str.charAt(str.length() - 1)); // !
    }
}
