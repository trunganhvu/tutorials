package String;

public class getChars {
    public static void main(String[] args) {
        String str = "Hello, World!";
        char[] charArray = new char[15];

        // Copy characters of range [0,5] into position 3 in array. If fill all remaining characters as 0 (number of unicode)
        str.getChars(0, 5, charArray, 3);

        // In ra mảng charArray
        System.out.print("Copied characters: ");
        for (char c : charArray) {
            System.out.print(c + " ");      //       H e l l o
        }

        System.out.println();
        String str2 = new String(charArray);
        str2.chars().forEach(System.out::println);  // 0 0 0 72 101 108 108 111 0 0 0 0 0 0 0
    }
}
