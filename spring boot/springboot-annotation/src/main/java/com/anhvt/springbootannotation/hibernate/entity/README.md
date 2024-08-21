# @Entity
Annotation đánh đấu object là 1 entity tương ứng với đối tượng table trong database

### Khi sử dụng @Entity
```java
@Entity
public class Book {
    @Id
    private int id;
    private String name;
}
```

SQL được gen
```sql
create table book (id integer not null, name varchar(255), primary key (id))
```

### Khi sử dụng @Entity(name = "tbl_book") thì sql sẽ chỉ định rõ tên table
```java
@Entity(name = "tbl_book")
public class Book {
    @Id
    private int id;
    private String name;
}
```

SQL được gen có tên table tương ứng
```sql
create table book (id integer not null, name varchar(255), primary key (id))
```
