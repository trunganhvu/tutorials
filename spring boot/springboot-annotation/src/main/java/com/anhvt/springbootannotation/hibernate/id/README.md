# @Id
Annotation đánh đấu trường là khoá chính, 1 entity có thể có 1 hoặc nhiều trường để làm khoá chính

### Sử dụng @Id để đánh đấu 2 trường là khoá chính
```java
@Entity
@Table(name = "tbl_stack")
public class Stack {
    @Id
    private int id;

    @Id
    private int pk;

    private String name;
}
```

SQL khi được gen ra thì có primary key (id, pk)
```sql
Hibernate: create table tbl_stack (pk integer not null, id integer not null, name varchar(255), primary key (id, pk))
```