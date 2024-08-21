# Subquery
Subquery là truy vấn con, là 1 câu truy vấn phụ của 1 câu truy vấn chính

```sql
SELECT * FROM customers
WHERE annual_purchases > (SELECT annual_purchases FROM customers WHERE id = 1);
```

# CTE (Common Table Expressions) 
CTE là một/hoặc một chuỗi các câu truy vấn được đặt tên và được xác định trước mỗi đầu cầu truy vấn chính và sau đó, truy vấn chính có thể truy cập được chúng giống như truy cập các bảng.

```sql
WITH cte_role AS (
    SELECT id, role_name
    FROM role
    WHERE role_name like 'admin%'
)
SELECT * FROM user
JOIN cte_role r ON 
    u.role_id = r.id 
    AND u.role_name = r.role_name;
```
# So sánh Subquery vs CTE
* CTEs được xác định trước mỗi câu truy vấn, trong khi đó Subqueries được xác định bên trong câu truy vấn.
* CTEs luôn được đặt tên, trong khi đó Subqueries chỉ phải đặt tên trong 1 vài trường hợp
* CTEs có thể sử dụng để đệ quy.
* CTEs dễ đọc hơn Subqueries ở những câu truy vấn báo cáo phức tạp.
* Một CTE có thể được sử dụng nhiều lần trong câu truy vấn, trong khi Subquery chỉ có thể sử dụng 1 lần. Điều này giúp câu code SQL ngắn hơn.
* Subqueries có thể sử dụng trong mệnh đề WHERE kết hợp với từ khoá IN hoặc EXISTS, nhưng CTEs thì không được.
* Subqueries có thể lấy 1 phần dữ liệu từ 1 bảng để cập nhật giá trị cho 1 bảng khác.

## Trường hợp đặc biệt CTE sử dụng với đê quy
```sql
-- Định nghĩa CTE đệ quy
WITH RECURSIVE ProductHierarchy AS (
    -- Phần CTE cơ bản: Chọn sản phẩm gốc
    SELECT product_id, product_name, parent_id
    FROM product
    WHERE product_id = 1 -- Thay đổi giá trị này tùy thuộc vào sản phẩm gốc bạn quan tâm

    UNION ALL

    -- Phần đệ quy: Chọn các sản phẩm con
    SELECT p.product_id, p.product_name, p.parent_id
    FROM product p
    INNER JOIN ProductHierarchy ph ON p.parent_id = ph.product_id
)
-- Sử dụng CTE trong câu truy vấn chính
SELECT product_id, product_name
FROM ProductHierarchy;
```
