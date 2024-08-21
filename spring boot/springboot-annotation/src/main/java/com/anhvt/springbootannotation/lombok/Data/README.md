# @Data
Annotation Data tương đương sử dụng @Getter + @Setter + @EqualsAndHashCod + @ToString

### Sử dụng Data 
```java
@Data
public class Product {
    private String name;
    private String color;
}
```

### Sử dụng các annotation ata khác hoàn toàn tương tụ các annotation sau
```java
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Product {
    private String name;
    private String color;
}
```

### Cách sử dụng tiện ích setter, getter, toString, hashCode, equals
```java
roduct product1 = new Product();
product1.setName("p1");
product1.setColor("c1");
System.out.println(product1);                   // Product(name=p1, color=c1)
System.out.println(product1.toString());        // Product(name=p1, color=c1)

System.out.println(product1.hashCode());        // 214338
System.out.println(product1.equals(new Product())); //false
```


### Code object sau khi compile
```java
public class Product {
    private String name;
    private String color;

    @Generated
    public Product() {
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
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setColor(final String color) {
        this.color = color;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Product)) {
            return false;
        } else {
            Product other = (Product)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$color = this.getColor();
                Object other$color = other.getColor();
                if (this$color == null) {
                    if (other$color != null) {
                        return false;
                    }
                } else if (!this$color.equals(other$color)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    @Generated
    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $color = this.getColor();
        result = result * 59 + ($color == null ? 43 : $color.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getName();
        return "Product(name=" + var10000 + ", color=" + this.getColor() + ")";
    }
}
```
