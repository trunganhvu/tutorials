package String;

public class strip {
    public static void main(String[] args) {
        String str1 = "   Hello, world!   ";
        String str2 = "\u2005Hello, world!\u2005"; // \u2005 is space in Unicode (Whitespace Character)

        // strip() access unicode charater, while trim() do not
        System.out.println("[" + str1.strip() + "]");   // [Hello, world!]
        System.out.println("[" + str2.strip() + "]");   // [Hello, world!]
        System.out.println("[" + str2.trim() + "]");    // [ Hello, world! ]
        
    }
}
