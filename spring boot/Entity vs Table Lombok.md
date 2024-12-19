# @Table(name = "tbl_user")
> name là tablename trong database. Sử dụng trong native query. 


# @Entity(name = "tbl_user")
> Name trong entity là table name dùng trong Query trong code

```java
@Query(value = "SELECT com.anhvt.demo.entity.User(t.id, t.username, t,email) FROM tbl_user t WHERE t.email = :email")
User findUserByEmail(String email);
```

Sử dụng tbl_user với User() objectobject