# @NaturalId
Annotation đánh dấu một hoặc nhiều trường trong entity có thể được coi là "khóa tự nhiên" (natural identifier)
Giúp Hibernate sẽ tối ưu hóa truy vấn dựa trên trường được sử dụng @NaturalId

### Sử dụng @NaturalId cho trường email, password, unique key
```java
@Entity
@Table(name = "tbl_account")
public class Account {
    @Id
    private Long id;

    private String name;

    @NaturalId
    private String email;

    @NaturalId(mutable = true)
    private String subEmail;
}
```

### SQL được gen
```sql
Hibernate: create table tbl_account (id bigint not null, email varchar(255), name varchar(255), sub_email varchar(255), primary key (id))
Hibernate: alter table if exists tbl_account drop constraint if exists UKrrviut63dn7j0liyr0xthrt0i
Hibernate: alter table if exists tbl_account add constraint UKrrviut63dn7j0liyr0xthrt0i unique (email, sub_email)
```