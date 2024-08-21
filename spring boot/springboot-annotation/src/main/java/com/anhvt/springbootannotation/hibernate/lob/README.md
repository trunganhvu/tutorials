# @Lob (Large OBject)
Annotation được sử dụng để chỉ định rằng một trường trong entity sẽ được ánh xạ tới một cột trong cơ sở dữ liệu có khả năng lưu trữ dữ liệu lớn như văn bản dài hoặc dữ liệu nhị phân.

### Sử dụng @Lob với chuỗi ký tự lớn hoặc dữ liệu nhị phân
```java
@Entity
@Table(name="tbl_media")
public class Media {
    @Id
    private int id;

    private String name;

    @Lob
    private byte[] images;

    @Lob
    private String content;
}
```

### SQL được gen
```sql
Hibernate: create table tbl_media (id integer not null, content clob, images blob, name varchar(255), primary key (id))
```