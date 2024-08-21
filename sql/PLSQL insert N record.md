# Insert N record same value into PLSQL

Table minh họa với 3 column đơn giản 

```sql
CREATE TABLE my_table (
	id NUMBER PRIMARY KEY,
	name VARCHAR2(100),
	description VARCHAR2(255)
);
commit;
```

Lệch insert 100 record

```sql
INSERT INTO my_table
	SELECT                    -- Chỉ định các fields cố định
		rownum,                 -- Chỉ định id tăng theo số record
		'name' || rownum,       -- name nối với id
		'description' || rownum
	from (select 0 from <customer or dual> WHERE rownum <= 100); -- Thực hiện select từ table đủ số lượng record
commit
```