# POJI vs POJO
## POJI

POJI là viết tắt của Plain Old Java Interface. Một POJI là một interface Java thuần, nó không extend interface của bất kì framework hay technology nào. Và POJI có thể extend các POJI interface

```java
public interface MyService { // Đây là một POJI
    void performAction();
}

public interface ExtendedService extends MyService { // Đây là một POJI
    void additionalAction();
}

// Lớp này triển khai POJI
public class MyServiceImpl implements ExtendedService {
    @Override
    public void performAction() {
        System.out.println("Performing action...");
    }

    @Override
    public void additionalAction() {
        System.out.println("Performing additional action...");
    }
}

// ---------------------------------
public interface MyServlet extends javax.servlet.Servlet { // Đây không phải POJI
    // Some themods
}
```

## POJO

POJO là viết tắt của Plain Old Java Object. Một POJO là một class Java thuần, tức là nó không extend/implement bất kỳ class/interface của framework hay một technology nào và POJO có thể extend/implement POJO và POJI.

**Sử dụng interface**

```java
public class MyClass1 {  // Đây là POJO
    private String name;
    private int age;

    // Setter
    // Getter
    // Contructor
}

public class MyClass2 implements Serializable { // Đây là POJO vì Serializable là một POJI
    // Some methods
}

public class MyClass3 extends MyClass1 implements MyInterface1 { // Đây là POJO vì extend POJO và implement POJI
    // Some methods
}

public interface MyInterface1 {
    public void Method1();
}

// ---------------------------------
public class MyClass4 implements javax.servlet.Servlet { // Đây không là POJO
    // Some methods
}

public class MyClass5 extends MyClass4 { // Đây không là POJO
    // Some methods
}
```