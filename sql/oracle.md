Oracle Database là Phân loại cơ sở dữ liệu được đáp ứng với nhiều lợi ích, được thiết kế để đảm bảo quản lý quản trị dữ liệu hiệu quả, tính sẵn sàng cao và hiệu suất mạnh mẽ.
Nội dung này được viết với mục tiêu chia sẻ về kiến trúc Oracle, các thành phần, luồng thực thi cơ bản với database Oracle và giới thiệu về việc Backup - Restore Oracle Database.
Phần 1: luồng tương tác thực thi trong database oracle - Oracle Database Work flow
Oracle thực thi request theo thứ tự cơ bản:
1. User Process - User tương tác --> được định nghĩa là các tương tác của người dùng với các hệ thống có CSDL, giao diện có giao tiếp, có ảnh hưởng và tương tác với dữ liệu --> có truy vấn cơ sở dữ liệu Oracle.
2. Connection to the Database: - kết nối đến cơ sở dữ liệu
Tạm gọi là Oracle Net Services - đối với phân loại nhóm database: gọi là phân loại kết nối chiều đến cơ sở dữ liệu.
Khi người dùng có thực thi, giao tiếp tại bước 1, thông qua giao thức mạng, Yêu cầu của người dùng được truyền qua mạng bằng cách sử dụng Oracle Net Services. Tầng này xử lý giao tiếp giữa máy khách và máy chủ cơ sở dữ liệu, đảm bảo truyền dữ liệu an toàn và hiệu quả.
3. Server Processes - Máy chủ thực thi:
Quá trình này xử lý việc thực thi các câu lệnh SQL và trả về kết quả cho quá trình người dùng.
Máy chủ xử lý dùng chung - Cluster or Distributed: Trong cấu hình máy chủ đa luồng, nhiều tiến trình sẽ cùng xử lý, song song hóa các tác vụ hoặc chuyên biệt hóa các module, nâng cao khả năng sử dụng tài nguyên và khả năng mở rộng.
4. Instance Components - Thành Phần cấu hình - các thành phần của Oracle nằm tại tầng này.
System Global Area (SGA): Lưu trữ các ô nhớ, phân vùng, cấu phần và dữ liệu của toàn bộ database, chứa dữ liệu và thông tin điều khiển của CSDL Oracle. - Instance
Database Buffer Cache: Lưu các bản nháp trả về từ dữ liệu truy vấn tạm thời, tối ưu thời gian truy vấn cũng như hiệu năng phản hồi. - thủ tục VIEW trong Oracle- cũng có thể coi là 1 DB buffer cache
Shared Pool: Chứa các câu lệnh SQL và PL/SQL được chia sẻ, bộ đệm từ điển dữ liệu và các cấu trúc điều khiển khác. phục vụ phân quyền, chia sẻ csdl liên kết hoặc thông luồng csdl tương quan - dạng database link share pool.
Redo Log Buffer: Lưu trữ tạm thời các mục REDO (các thay đổi được thực hiện đối với cơ sở dữ liệu) trước khi ghi vào redo log file để chuyển sang Archive.
Large Pool: Sử dụng cho quy các đầu công việc cấp phát yêu cầu tài nguyên lớn, như backup sao lưu và restore khôi phục tương tác hoạt động của Database, và đối với cấp memory theo phiên hoặc chia sẻ dạng bộ nhớ động phục vụ khai thác dữ liệu giữa các node trong cụm hoặc trong cùng môi trường...
Java Pool: thực thi java trong oracle
Streams Pool: Được sử dụng bởi Oracle Streams để sao chép và tích hợp dữ liệu.
Program Global Area (PGA): PGA là vùng bộ nhớ riêng cho từng quy trình máy chủ, chứa dữ liệu và thông tin kiểm soát cụ thể cho quy trình, chẳng hạn như không gian sắp xếp và thông tin phiên - session.
5. SQL Execution
Về cơ bản sẽ có các bước:
Phân tích cấu trúc, cú pháp, phiên dịch
Tối ưu và phân tải tạo thứ tự thực thi tối ưu - lập kế hoạch thực thi
Công cụ Thực thi: Thực thi theo kế hoạch đã được thiết lập, tương tác với bộ đệm đệm để tìm nạp hoặc sửa đổi dữ liệu và quản lý kiểm soát giao dịch.
Parser --> Optimizer --> Execution Engine:
6. Data Storage
Datafiles: Files dữ liệu trên đĩa được định danh là dữ liệu của database - database''s data. thay đổi dữ liệu--> datafile cũng sẽ thay đổi.
Redo Log Files: Files lưu lại toàn bộ thay đổi với database, đảm bảo phục hồi dữ liệu trong trường hợp bị lỗi.
Control Files: Chứa siêu dữ liệu Medatada database về cơ sở dữ liệu, chẳng hạn như cấu trúc của cơ sở dữ liệu và vị trí của tệp dữ liệu và tệp nhật ký làm lại.
Archive Log Files: Bản sao lưu của Redo log files, được sử dụng cho mục đích khôi phục.
7. Transaction Management - Quản trị giao dịch
Undo Tablespace
Commit and Rollback
Recovery metadata/tables
8. Data Access and Concurrency Control - Truy vấn dữ liệu và kiểm soát đồng thời
Locks and Latches
Isolation Levels
9. Background Processes - Các tiến trình nền của database
DBWn (Database Writer): ghi dữ liệu cơ bản
LGWR (Log Writer): tạo log truy vấn, phiên, log tương tác và ghi log Redo của database
CKPT (Checkpoint Process): các checkpoint cho phép đưa dữ liệu về thời điểm tạo checkpoint theo phiên/bảng/schema/databases
SMON (System Monitor): Thực hiện các hoạt động khôi phục và quản lý không gian nhớ gồm ram/storage/sector...
PMON (Process Monitor): Dọn dẹp sau các quy trình không thành công và giải phóng tài nguyên.
10. Recovery and Backup - Sao lưu và Khôi Phục
RMAN (Recovery Manager): công cụ Tiện ích sao lưu và phục hồi của Oracle, cung cấp giải pháp toàn diện về bảo vệ dữ liệu.
Flashback Technology: Cho phép khôi phục nhanh chóng khi dữ liệu bị hỏng hoặc thay đổi ngẫu nhiên bằng cách cho phép khôi phục tại thời điểm và hoàn tác các thay đổi ở cấp hàng/bản ghi trong bảng.
11. Tối ưu hóa hiệu năng:
AWR (Automatic Workload Repository):Thu thập số liệu thống kê hiệu suất và cung cấp dữ liệu để điều chỉnh và chẩn đoán sức khỏe của Databases.
ADDM (Automatic Database Diagnostic Monitor): Phân tích dữ liệu AWR và đưa ra các đề xuất để cải thiện hiệu suất.
Oracle Enterprise Manager: Giao diện dựa trên web để giám sát và quản lý cơ sở dữ liệu Oracle, cung cấp các công cụ để điều chỉnh hiệu suất, quản lý tài nguyên và khắc phục sự cố.
Các lưu ý với CSDL Oracle và các loại CSDL:
Luôn kiểm tra hệ thống backup và redo có hoạt động - và trạng thái hoạt động luôn ở mức tốt.
Các phân nhóm backup được quản trị theo phiên, thời gian và chu kì.
Định kì kiểm tra sức khỏe của Storage và Database.
Additional Tips
Monitor Logs: Continuously monitor Oracle alert logs and RMAN logs for any errors or warnings during the recovery process.
Test Restores Regularly: Periodically test the restore process to ensure that backups are reliable and recovery procedures are well understood.
Implement High Availability Solutions: Use Oracle Data Guard or Real Application Clusters (RAC) to provide high availability and minimize downtime in case of storage failures.
Use ASM (Automatic Storage Management): ASM can provide better management and availability of storage resources in Oracle environments.
Document về Oracle Architecture
https://docs.oracle.com/database/121/CNCPT/startup.htm...
https://www.oracle.com/.../pdf/Database%20Architecture.pdf
Kiến trúc chi tiết của Oracle 12C
https://www.oracle.com/.../poster/output_poster/poster.html#
Oracle Database 19c Technical Architecture:
https://www.oracle.com/.../19/pdf/db-19c-architecture.pdf