# Run containers
Run primary and replica (read only) in background:
```bash
docker-compose up -d postgres_primary postgres_replica
```

Stop all services and remove volumes:
```bash
docker-compose down -v
```

Connect to primay database and create tables, insert data -> Replicate Database asyning

# Explain docker-compose
## Common
```yml
version: '3.8'
x-postgres-common:
  &postgres-common
  image: postgres:14-alpine
  user: postgres
  restart: always
  healthcheck:
    test: 'pg_isready -U user --dbname=postgres'
    interval: 10s
    timeout: 5s
    retries: 5
```
* version: '3.8': Chỉ định phiên bản của Docker Compose.
* x-postgres-common: Đây là một phần khai báo cấu hình chung (x-postgres-common) mà sau này sẽ được tham chiếu lại (dùng lại) trong các dịch vụ khác bằng cách sử dụng cú pháp <<: *postgres-common.
* image: postgres:14-alpine: Sử dụng hình ảnh Docker của PostgreSQL phiên bản 14 trên hệ điều hành Alpine (một hệ điều hành nhẹ).
* healthcheck: Kiểm tra tình trạng hoạt động của container PostgreSQL thông qua lệnh pg_isready:
    * test: Test connection bằng lệnh pg_isready -U user --dbname=postgres.
    * interval: Mỗi 10 giây thực hiện kiểm tra.
    * timeout: Mỗi lần kiểm tra có tối đa 5 giây.
    * retries: Nếu lệnh kiểm tra không thành công, sẽ thử lại tối đa 5 lần.

## Primary service
```yml
services:
  postgres_primary:
    <<: *postgres-common
    ports:
      - 5432:5432
    environment:
      POSTGRES_USER: user
      POSTGRES_DB: postgres
      POSTGRES_PASSWORD: password
      POSTGRES_HOST_AUTH_METHOD: "scram-sha-256\nhost replication all 0.0.0.0/0 md5"
      POSTGRES_INITDB_ARGS: "--auth-host=scram-sha-256"
    command: |
      postgres 
      -c wal_level=replica 
      -c hot_standby=on 
      -c max_wal_senders=10 
      -c max_replication_slots=10 
      -c hot_standby_feedback=on
    volumes:
      - ./00_init.sql:/docker-entrypoint-initdb.d/00_init.sql
```
* postgres_primary: Name của primary database
* <<: *postgres-common: Thừa kế tất cả các cấu hình từ phần x-postgres-common.
* ports: - 5432:5432: liên kết local port <=> container port
* environment: Cấu hình các biến môi trường cho PostgreSQL:
    * POSTGRES_USER: Tạo người dùng cơ sở dữ liệu là user.
    * POSTGRES_DB: Tạo cơ sở dữ liệu mặc định tên là postgres.
    * POSTGRES_PASSWORD: Mật khẩu cho người dùng user.
    * POSTGRES_HOST_AUTH_METHOD: Phương thức xác thực cho kết nối từ các máy chủ khác (dùng SCRAM-SHA-256 cho người dùng và MD5 cho kết nối replication).
    * POSTGRES_INITDB_ARGS: Các đối số khi khởi tạo cơ sở dữ liệu, cụ thể là phương thức xác thực scram-sha-256 cho kết nối host.
* command: Câu lệnh khởi chạy PostgreSQL với các cấu hình replication:
    * wal_level=replica: Cấu hình chế độ ghi nhật ký giao dịch (WAL) ở mức "replica", nghĩa là cho phép sao chép dữ liệu.
    * hot_standby=on: Bật chế độ standby cho phép đọc dữ liệu trên server replica.
    * max_wal_senders=10: Số lượng tối đa các tiến trình gửi WAL cho các replica.
    * max_replication_slots=10: Số lượng tối đa các replication slots có thể tạo.
    * hot_standby_feedback=on: Bật tính năng gửi phản hồi từ replica để cải thiện việc xử lý WAL.
* volumes: Gắn file SQL 00_init.sql vào thư mục /docker-entrypoint-initdb.d/ trong container, nơi PostgreSQL sẽ tự động chạy các script SQL khi khởi động.

## Init sql
```sql
create user replicator with replication encrypted password 'replicator_password';
select pg_create_physical_replication_slot('replication_slot');
```
* create user replicator: Tạo user replicator với quyền replication.
* pg_create_physical_replication_slot('replication_slot'): Tạo một replication slot để PostgreSQL có thể quản lý các kết nối sao chép (replication connections).

## Replica service
```yml
  postgres_replica:
    <<: *postgres-common
    ports:
      - 5433:5432
    environment:
      PGUSER: replicator
      PGPASSWORD: replicator_password
    command: |
      bash -c "
      until pg_basebackup --pgdata=/var/lib/postgresql/data -R --slot=replication_slot --host=postgres_primary --port=5432
      do
      echo 'Waiting for primary to connect...'
      sleep 1s
      done
      echo 'Backup done, starting replica...'
      chmod 0700 /var/lib/postgresql/data
      postgres
      "
    depends_on:
      - postgres_primary
```
* postgres_replica: Name của replica database.
* <<: *postgres-common: Thừa kế các cấu hình chung từ phần x-postgres-common.
* ports: - 5433:5432: liên kết local port <=> container port
* environment: Cấu hình các biến môi trường cho PostgreSQL replica:
    * PGUSER: Người dùng replicator cho replica.
    * PGPASSWORD: Mật khẩu cho người dùng replicator.
* command: Câu lệnh khởi động replica:
    * pg_basebackup: Lệnh sao lưu cơ sở dữ liệu từ server chính (primary) vào dữ liệu của replica.
    * until pg_basebackup ...: Lặp lại việc sao lưu đến khi thành công, sau đó khởi động replica.
    * chmod 0700 /var/lib/postgresql/data: Đảm bảo quyền truy cập đúng cho thư mục dữ liệu.
* depends_on: Đảm bảo rằng dịch vụ postgres_primary phải khởi động trước khi postgres_replica bắt đầu.