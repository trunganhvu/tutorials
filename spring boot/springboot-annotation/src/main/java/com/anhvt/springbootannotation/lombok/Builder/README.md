# @Builder
Annotation cho sử dụng builder pattern để khởi tạo class
Nhược điểm: Không hỗ trợ kế thừa. Nếu các lớp con kế thừa từ một lớp cha, @Builder sẽ không cho phép bạn kế thừa builder từ lớp cha một cách dễ dàng.

### Car bình thường
```shell
@Setter
@Getter
public class Car {
    private String name;
    private String color;
}
```

### Car sử dụng @Builder
```shell
@Builder
@Getter
public class Car {
    private String name;
    private String color;
}
```


### Cách sử dụng
```shell
Car car = Car.builder()     // Builder là design pattern
            .name("item1")  // setter cho field name tương tự setName("")
            .color("black") // setter cho field color
            .build();       // final step to create new object

        System.out.println(car.getName());      // item1
        System.out.println(car.getColor());     // black
```

### Tạo bộ code sau khi compile
```java
public class Car {
    private String name;
    private String color;

    @Generated
    Car(final String name, final String color) {
        this.name = name;
        this.color = color;
    }

    @Generated
    public static CarBuilder builder() {
        return new CarBuilder();
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getColor() {
        return this.color;
    }

    @Generated
    public static class CarBuilder {
        @Generated
        private String name;
        @Generated
        private String color;

        @Generated
        CarBuilder() {
        }

        @Generated
        public CarBuilder name(final String name) {
            this.name = name;
            return this;
        }

        @Generated
        public CarBuilder color(final String color) {
            this.color = color;
            return this;
        }

        @Generated
        public Car build() {
            return new Car(this.name, this.color);
        }

        @Generated
        public String toString() {
            return "Car.CarBuilder(name=" + this.name + ", color=" + this.color + ")";
        }
    }
}

```