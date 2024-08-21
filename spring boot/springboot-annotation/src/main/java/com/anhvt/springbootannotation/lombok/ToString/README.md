# @EqualsAndHashCode
Annotation triển khai phương thực hashCode và equals cho đối tượng

Các param:
* **exclude**: exclude = {""} - ngoại trừ field chỉ định, không sử dụng cùng onlyExplicitlyIncluded
* **doNotUseGetters**: doNotUseGetters = true - Trong toString() không sử dụng getter mà sử dụng this.field
* **of**: of = {"field", ""field"} - chỉ định fields được dùng trong toString()
* **callSuper**: callSuper = true - Chỉ định sử dụng toString() của parent class
* **onlyExplicitlyIncluded**: onlyExplicitlyIncluded = true - Chỉ đỉnh các fields sử dụng trong toString() được đánh dấu bằng @EqualsAndHashCode.Include
### Cách sủ dụng doNotUseGetters
```java
@ToString(doNotUseGetters = true)
public class Phone extends Device{
    private int id = 1;
    private String name = "iphone";
}
```

Code sau khi compile toString sử dụng this.id thay cho getId()
```java
@Generated
public String toString() {
    return "Phone(id=" + this.id + ", name=" + this.name + ")";
}
```

### Cách sử dụng onlyExplicitlyIncluded
```java
@ToString(onlyExplicitlyIncluded = true)
public class Phone {
    @ToString.Include
    private int id = 1;
    @ToString.Exclude
    private String name = "iphone";
}
```

Sau khi compile chỉ có chứa id
```java
@Generated
public String toString() {
    return "Phone(id=" + this.id + ")";
}
```

### Cách sử dụng exclude
```java
@ToString(exclude = {"id"})
public class Phone {
    private int id = 1;
    private String name = "iphone";
}
```

Sau khi compile chỉ có chứa name
```java
@Generated
public String toString() {
    return "Phone(name=" + this.name + ")";
}
```

### Cách dùng of
```java
@ToString(of = {"id"})
public class Phone {
    private int id = 1;
    private String name = "iphone";
}
```

Sau khi compile chỉ có id được sử dụng
```java
@Generated
public String toString() {
    return "Phone(id=" + this.id + ")";
}
```

### Cách sử dụng callSuper toString có dạng: Phone(super=Device(code=Device object), id=1, name=iphone)
```java
public class Device {
    private String code = "Device object";
}

@ToString(callSuper = true)
public class Phone extends Device{
    private int id = 1;
    private String name = "iphone";
}
```

Sau khi compile toString() có call thêm phương thức toString() của super class
```java
@Generated
public String toString() {
    String var10000 = super.toString();
    return "Phone(super=" + var10000 + ", id=" + this.id + ", name=" + this.name + ")";
}
```