package singleresponsibility;

/**
 * Single responsibility is every class, method have a responsibility for object
 * (Entity only have data, UserService only maniputor for user, not check authentication or action logout
 */
public class Book {

    private String name;
    private String author;
    private String text;

    //constructor, getters and setters

    // This methods are should right here
    //  ||
    //  ||
    //   V
    // methods that directly relate to the book properties
    public String replaceWordInText(String word, String replacementWord){
        return text.replaceAll(word, replacementWord);
    }

    public boolean isWordInText(String word){
        return text.contains(word);
    }
}
