# @GeneratedValue
Annotation sử dụng để tự động tạo giá trị cho khóa chính (primary key) của một entity. Tuy nhiên, @GeneratedValue phải luôn đi kèm với @Id vì nó chỉ có ý nghĩa khi gắn với một trường được đánh dấu là khóa chính.

### Khi sử dụng trong entity đi kèm với @Id và sử dụng @IdClass cho composite key
```java
@Entity(name = "tbl_employee")
@Table
@IdClass(EmployeeId.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fieldId1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int fieldId2;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String fieldId3;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int fieldId4;
}
```

```java
@Setter
@Getter
public class EmployeeId implements Serializable {
    private Long id;

    private int fieldId1;

    private int fieldId2;

    private int fieldId3;

    private int fieldId4;
}
```

### Sql được tạo ra
```sql
Hibernate: create table tbl_employee (field_id1 integer not null, field_id2 integer not null, field_id3 varchar(255) not null, field_id4 integer not null, id bigint not null, name varchar(255), primary key (field_id1, field_id2, field_id3, field_id4, id))
    Hibernate: create sequence tbl_employee_seq start with 1 increment by 50
```