# @Value
Annotation định nghĩa object immutable. Giá trị của field chỉ có thể định nghĩa qua constructor 
Cách sử dụng giống vói Record

### Khi sử dụng @Value thì Setter sẽ không được tạo (code @Setter ở trước cũng không được tạo)
```java
@Setter
@Value
@AllArgsConstructor
@Getter
public class EntityData {
    private String name;
    private String title;
}
```

Class sẽ khi được compile thì class sẽ thành static và không có setter
```java
public final class EntityData {
    private final String name;
    private final String title;

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof EntityData)) {
            return false;
        } else {
            EntityData other = (EntityData)o;
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
                if (other$name != null) {
                    return false;
                }
            } else if (!this$name.equals(other$name)) {
                return false;
            }

            Object this$title = this.getTitle();
            Object other$title = other.getTitle();
            if (this$title == null) {
                if (other$title != null) {
                    return false;
                }
            } else if (!this$title.equals(other$title)) {
                return false;
            }

            return true;
        }
    }

    @Generated
    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getName();
        return "EntityData(name=" + var10000 + ", title=" + this.getTitle() + ")";
    }

    @Generated
    public EntityData(final String name, final String title) {
        this.name = name;
        this.title = title;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }
}
```