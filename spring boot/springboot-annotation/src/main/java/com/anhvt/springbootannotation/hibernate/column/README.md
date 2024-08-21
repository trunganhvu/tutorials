# @Column
Annotation định nghĩa thuộc tính tương ứng với column trong table database. @Column có các thuộc tính chỉ định sau:
* name: Tên của cột trong cơ sở dữ liệu mà trường này sẽ ánh xạ đến
* unique: Chỉ định giá trị có duy nhất không
* nullable: Chỉ định có cho phép null không
* insertable: Xác định liệu cột này có được bao gồm trong câu lệnh SQL INSERT hay không.
* updatable: Xác định liệu cột này có được bao gồm trong câu lệnh SQL UPDATE hay không.
* columnDefinition: Định nghĩa cụ thể của cột trong SQL. Bạn có thể sử dụng thuộc tính này để chỉ định loại dữ liệu SQL cụ thể hoặc ràng buộc khác.
* table: hỉ định tên của bảng mà cột này thuộc về. Thuộc tính này hữu ích khi bạn đang ánh xạ entity đến nhiều bảng (ví dụ trong trường hợp sử dụng @SecondaryTable).
* length:  Độ dài tối đa của cột cho các kiểu dữ liệu chuỗi (String). Đây thường được sử dụng với kiểu dữ liệu VARCHAR
* precision: Độ chính xác tổng số chữ số của số thập phân (được sử dụng với các kiểu dữ liệu BigDecimal hoặc Double).
* scale: Số lượng chữ số thập phân được lưu trữ sau dấu thập phân (được sử dụng với các kiểu dữ liệu BigDecimal

### Khi sử dụng @Column với các chỉ định hay gặp
```java
@Entity(name = "tbl_event")
@Table
public class Event {
    @Id
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_name", unique = true, nullable = false, length = 30)
    private String name;

    @Column(name = "event_title", insertable = false, updatable = false)
    private String title;

    @Column(name = "event_price", precision = 0)
    private Double price;

    @Column(name = "event_cost", scale = 0)
    private Double cost;
}
```


### SQL được gen ra
```sql
Hibernate: create table tbl_event (event_id bigint not null, event_cost float(53), event_name varchar(30) not null, event_price float(53), event_title varchar(255), primary key (event_id))

```