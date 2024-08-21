# @Table
Annotation đánh đấu object sẽ tạo table tương ứng vào trong database.
@Table phải sử dụng cùng @Entity thì mới có thể create table vào database. Khi create table có thể chỉ định trực tiếp
* schema: schema = "schema1" -  chỉ định schema trong database tương ứng
* name: name = "tbl_story" - chỉ định tên table khi create table sql
* uniqueConstraints: uniqueConstraints = { @UniqueConstraint(columnNames = "name")} - Chỉ định tập hợp các trường duy nhất
* indexes: indexes = { @Index(name = "idx_id_name", columnList = "id, name")} - chỉ định index cho table


### Khi chỉ sử dụng @Table thì không tự động tạo table
```java
@Table
public class Story {
    @Id
    private int id;
    private String name;
}
// OR
@Table(
        name = "tbl_story",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        },
        indexes = {
                @Index(name = "idx_email_name", columnList = "email, name")
        }
)
public class Story {
    @Id
    private int id;
    private String name;
    private String email;
}
```

### @Entity + @Table và các thiết định name, uniques, indexs
```java
@Entity
@Table(
        name = "tbl_story",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        },
        indexes = {
                @Index(name = "idx_email_name", columnList = "email, name")
        }
)
public class Story {
    @Id
    private int id;
    private String name;
    private String email;
}
```

SQL được gen với lệch create table, create index, alter table để add constraint
```sql
Hibernate: create table tbl_story (id integer not null, email varchar(255), name varchar(255), primary key (id))
Hibernate: create index idx_email_name on tbl_story (email, name)
Hibernate: alter table if exists tbl_story drop constraint if exists UKifuu9spq1mj80a7y7a0c5ran7
Hibernate: alter table if exists tbl_story add constraint UKifuu9spq1mj80a7y7a0c5ran7 unique (name)
```
