# @Accessors
Annotation cho phép mở rộng cho getter, setter

### Person bình thường
```shell
@Getter
@Setter
public class Person {
    private String name;
    private int age;
    private String sAddress;
}
```

### Person sử dụng @Accessors
```shell
@Getter
@Setter
@Accessors(fluent = true, chain = true, prefix = {"s", ""})
public class Person {
    private String name;
    private int age;
    private String sAddress;
}
```
* fluent loại bỏ tiền tố get, set cho getter và setter method. getName()/setName(value) -> name()/name(value)
* chain là true thì setter method trả về this thay vì void. Cách này khá giống với Builder pattern. Ví dụ ở dưới
* prefix = {"s", ""} để loại bổ tiền tố chỉ định khi sử dụng thuộc tính trong object.

P/s: prefix chỉ gen setter/getter với thuộc tính thoả mãn tiền tố. Các thuộc tính không thoả mãn tiền tố sẽ không được tạo getter/setter


### Cách sử dụng 
```shell
Person person = new Person();
person.name("name")           // Setter() bình thường sẽ là setName("name")
      .age(1);                // Method age(1) tương tự như builder
person.address("address");    // Setter()/Getter() sẽ không có tiền tố s như field: sAddress
System.out.println(person.name());  // Getter() bình thờng là getName()
```

### Tạo bộ code sau khi compile
```java
public class Person {
    private String name;
    private int age;
    private String sAddress;

    public Person() {
    }

    @Generated
    public String name() {
        return this.name;
    }

    @Generated
    public int age() {
        return this.age;
    }

    @Generated
    public String address() {
        return this.sAddress;
    }

    @Generated
    public Person name(final String name) {
        this.name = name;
        return this;
    }

    @Generated
    public Person age(final int age) {
        this.age = age;
        return this;
    }

    @Generated
    public Person address(final String sAddress) {
        this.sAddress = sAddress;
        return this;
    }
}
```