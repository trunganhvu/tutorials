package String;

public class trim {
    public static void main(String[] args) {
        String str1 = "   Hello, world!   ";
        String str2 = "\u2005Hello, world!\u2005"; // \u2005 is space in Unicode (Whitespace Character)
        System.out.println("[" + str1.trim() + "]");   // [Hello, world!]
        System.out.println("[" + str2.trim() + "]");    // [ Hello, world! ]
    }
}
