# @Setter & @Getter
Annotation tự động tạo getter, setter cho các fields giúp giảm boilerplate code

### Cách sử dụng
Khai báo setter, getter
```java
@Setter
@Getter
public class Place {
    private String id;
    private String name;

    @Setter(AccessLevel.PROTECTED)  // access modify for setter is protected for this field
    private String address;
}
```

Sử dụng setter, getter như bình thường
```java
Place p1 = new Place();
        p1.setId("1");
        p1.setName("name1");
        p1.setAddress("11");        // access modify is PROTECTED
        System.out.println(p1.getId());     // 1
        System.out.println(p1.getName());   // name1
        System.out.println(p1.getAddress());// 11
```

### Code sau khi compile
```java
public class Place {
    private String id;
    private String name;
    private String address;

    public Place() {
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getAddress() {
        return this.address;
    }

    @Generated
    protected void setAddress(final String address) {
        this.address = address;
    }
}
```