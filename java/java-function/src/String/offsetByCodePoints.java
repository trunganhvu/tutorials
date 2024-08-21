package String;

public class offsetByCodePoints {
    public static void main(String[] args) {
        String str = "Hello, World!";
        int index = 6; // Position of 'W' is 6
        int offset = 3; // offset is 3 as Unicode
        int newIndex = str.offsetByCodePoints(index, offset); // new position of 'W' is 9: (`W`: 6, move 3 Unicode) =>  9
        System.out.println("New position after moved: " + newIndex);
        System.out.println(str);

    }
}
