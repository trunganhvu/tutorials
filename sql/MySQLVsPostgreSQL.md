# Các tính năng hữu ích
* UPDATE: MySQL thực hiện tốt hơn do ghi đè trực tiếp, trong khi PostgreSQL đánh dấu bản cũ và thêm dòng mới (xmax, xmin).
* PostgreSQL: Hỗ trợ nhiều ngôn ngữ cho stored procedure, bao gồm Python.
* PostgreSQL: Nghiêm ngặt hơn với kiểu dữ liệu và yêu cầu phải cast rõ ràng. Mysql thì lỏng lẻo hơn

# Cơ chế mặc địch
* MySQL: Mặc định là REPEATABLE-READ, có thể gặp vấn đề với phantom-read.
* PostgreSQL: Mặc định là READ-COMMITTED, giúp tránh xung đột tốt hơn
