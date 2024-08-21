# @Transient
Annotation @Transient trong Hibernate (và JPA) được sử dụng để chỉ ra rằng một thuộc tính của entity sẽ không được ánh xạ tới bất kỳ cột nào trong cơ sở dữ liệu. Điều này có nghĩa là Hibernate sẽ bỏ qua thuộc tính này khi thực hiện các thao tác như INSERT hoặc UPDATE

Code sau thể hiện trạng thái transient
```java
Employee employee = new Employee();
employee.setName("John Doe");   // object chưa được lưu vào cơ sở dữ liệu
```

### Sử dụng trong entity
```java
@Entity(name = "tbl_home")
@Table
public class Home {
    @Id
    private Long id;

    private String name;

    private String cost;

    @Transient
    private String version;
}
```

### SQL được gen hoàn toàn không có version. version chỉ được sử dụng trong code với mục đích khác
```sql
Hibernate: create table tbl_home (id bigint not null, cost varchar(255), name varchar(255), primary key (id))
```