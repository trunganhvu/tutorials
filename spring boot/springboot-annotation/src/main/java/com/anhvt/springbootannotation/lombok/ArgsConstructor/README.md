# @NoArgsConstructor & @AllArgsConstructor
Annotation chỉ định tự động tạo constructor không tham số và constructor full tham số cho object

### Khi sử dụng 
```java
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String name;
    private String author;
}
```

### Khi sử dụng
```java
Book book1 = new Book();        // @NoArgsConstructor
book1.setName("book1");
book1.setAuthor("author1");
System.out.println(book1.getName() + " " + book1.getAuthor());  // book1 author1

Book book2 = new Book("book2", "author2");  // @AllArgsConstructor
System.out.println(book2.getName() + " " + book2.getAuthor());  // book2 author2
```


### Code sau khi được compile
Constructor Book() và Book(final String name, final String author) đã được tạo
```java
private String name;
    private String author;

    @Generated
    public Book() {
    }

    @Generated
    public Book(final String name, final String author) {
        this.name = name;
        this.author = author;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getAuthor() {
        return this.author;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setAuthor(final String author) {
        this.author = author;
    }
```