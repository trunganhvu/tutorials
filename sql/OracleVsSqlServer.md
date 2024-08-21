Phân biệt oracle vs sql server
1. Cơ chế lưu data để rollback (oracle: undo - sql server: temp)
2. Leo thang lock. Ví dụ số lượng transaction chiếm 80% rows thì (oracle: k lock phần còn lại - sql server: lock toàn bộ bảng)
3. Sub partition oracle có hỗ trợ, sql server không hỗ trợ
4. Oracle hỗ trợ paralle (CPU chạy đa luồng) với select, update, insert,..
   Sql server chỉ hỗ trợ select, còn với update, insert thì cơ bản là tuần tự
5. Sự ưu việt trong phân tích hiệu năng và xử lý sự cố AWR 
-> KHI TỐI ƯU ORACLE CHẬM, THÌ XEM Ở WAIT CHỨ KHÔNG XEM QUERY
6. Flashback data từ undo, 
7. Cơ chế lưu data của orale: ASM