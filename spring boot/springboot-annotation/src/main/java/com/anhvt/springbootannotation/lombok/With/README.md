# @With
Annotation cho phép clone object ban đầu với các fields có thể thay đổi (Bắt buộc thay đổi từ 1 field)

### Code định nghĩa object với with
```java
@With
@AllArgsConstructor
@ToString
public class App {
    private String name;
    private String tech;
}
```

### Clone object với thay đổi field name
```java
public static void main(String[] args) {
    App app = new App("x", "java");
    System.out.println(app);        // App(name=x, tech=java)
    App app1 = app.withName("teams");
    System.out.println(app1);       // App(name=teams, tech=java)
}
```

Code sau khi compile
```java
public class App {
    private String name;
    private String tech;

    @Generated
    public App withName(final String name) {
        return this.name == name ? this : new App(name, this.tech);
    }

    @Generated
    public App withTech(final String tech) {
        return this.tech == tech ? this : new App(this.name, tech);
    }

    @Generated
    public App(final String name, final String tech) {
        this.name = name;
        this.tech = tech;
    }

    @Generated
    public String toString() {
        return "App(name=" + this.name + ", tech=" + this.tech + ")";
    }
}
```