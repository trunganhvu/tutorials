# **Java 8**

- Collections & Streams
- **Interface Defaults and Static Methods**
- **Functional interface**
- **Lambda expression**
- **Method Reference (dấu :: trong User::isRealUser, User::new)**
- **Stream API**
- Date and Time API support
- Optional support
- Parallel processing of array sorting

## Demo
Collections & Streams
```java
List<String> list = Arrays.asList("franz", "ferdinand", "fiel", "vom", "pferd");
list.stream()
    .filter(name -> name.startsWith("f"))
    .map(String::toUpperCase)
    .sorted()
    .forEach(System.out::println);
```
Lambda expression before java 8
```java
 Runnable runnable = new Runnable(){
    @Override
    public void run(){
        System.out.println("Hello world !");
    }
};
```
Lambda expression in java 8
```java
Runnable runnable = () -> System.out.println("Hello world two!");
```

# **Java 9**
- Addin new methods in Collections, Streams, Optionals, static method in Interfaces
```java
public interface MyInterface {
    private static void myPrivateMethod(){
        System.out.println("Yay, I am private!");
    }
}
```

# **Java 10**
- Var-keyword
```java
// Pre-Java 10
String myName = "Marco";

// With Java 10
var myName = "Marco"
```

# **Java 11**

- **Add new methods to the String class**
- Add new methods to java.nio.file.Files class
- **Add new methods to the collection interface**
- Add new methods to the Predicate interface
- **Using local variable Var in lambda**
- Local-Variable Type Inference (var) for lambda parameters

# **Java 16**
- Pattern Matching for instanceof
```java
// Before
if (obj instanceof String) {
    String s = (String) obj;
    // e.g. s.substring(1)
}
// After
if (obj instanceof String s) {
    // Let pattern matching do the work!
    // ... s.substring(1)
}
```

# **Java 17**

- **record class keyword available**
- **Official support for Apple M1 and later processor-based products**
- Added random number generation API
- Sealed Class officially added (sealed keyword)
- Text block function available when using multiple lines of String (EX: """""")
- Improved NumberFormat and DateTimeFormatter functionality
- **Stream.toList() available**
- **Pattern Matching for switch**

# **Java 21**

- Virtual Threads
- Sequenced Collections
- Pattern Matching for switch with null