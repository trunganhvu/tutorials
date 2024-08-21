package String;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class getBytes {
    public static void main(String[] args) {
        String str = "Hello, World!";

        // String -> byte[] unicode of character
        byte[] byteArray = str.getBytes();

        for (byte b : byteArray) {
            System.out.print(b + " ");  // 72 101 108 108 111 44 32 87 111 114 108 100 33
        }
        System.out.println();


        // void getBytes(int srcBegin, int srcEnd, byte[] dst, int dstBegin)
        String str2 = "Hello, World!";
        byte[] byteArray2 = new byte[str.length()];
        str2.getBytes(0, 5, byteArray2, 0); // Copy 5  charactor in range and fill all remaining characters as 0
        for (byte b : byteArray2) {
            System.out.print(b + " ");  // 72 101 108 108 111 0 0 0 0 0 0 0 0
        }
        System.out.println();

        // VD3:
        // byte[] getBytes(String charsetName)
        String str3 = "Hello, World!";
        byte[] byteArray3 = str3.getBytes(StandardCharsets.UTF_8);
        System.out.println(byteArray3);
    }
}
