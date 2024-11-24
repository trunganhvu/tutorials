# Config files

# Docker network
```sh
docker network create postgres
```

# Docker run primary database
```sh
docker run -it --rm --name postgres-1 `
--net postgres `
-e POSTGRES_USER=postgresadmin `
-e POSTGRES_PASSWORD=admin123 `
-e POSTGRES_DB=postgresdb `
-e PGDATA="/data" `
-v ${PWD}/postgres-1/pgdata:/data `
-v ${PWD}/postgres-1/config:/config `
-v ${PWD}/postgres-1/archive:/mnt/server/archive `
-p 5000:5432 `
postgres:15.0 -c 'config_file=/config/postgresql.conf'
```

# Exec primary database
```sh
docker exec -it postgres-1 bash
```
# Primary database create replica user
```sh
# Create replicator
createuser -U postgresadmin -P -c 5 --replication replicationUser

# Use database
psql --username=postgresadmin postgresdb

# Create table
CREATE TABLE customers (firsname text, customer_id serial, date_created timestamp);
```

# Start container and copy data from primary database (IMPORTANT)
```sh
# Run container
docker run -it --rm `
--net postgres `
-v ${PWD}/postgres-2/pgdata:/data `
--entrypoint /bin/bash postgres:15.0

# Copy data from primary database to /data/
pg_basebackup -h postgres-1 -p 5432 -U replicationUser -D /data/ -Fp -Xs -R

# Exit and create replica database
exit
```
* pg_basebackup: Đây là công cụ PostgreSQL dùng để sao lưu dữ liệu từ một máy chủ PostgreSQL. Nó sao lưu toàn bộ dữ liệu cơ sở dữ liệu và tạo một bản sao chính xác của cơ sở dữ liệu hiện tại.
* -h postgres-1: Chỉ định máy chủ chính (primary) để sao lưu dữ liệu từ đó.
* -p 5432: Chỉ định cổng kết nối PostgreSQL (mặc định là 5432).
* -U replicationUser: Tên người dùng có quyền sao chép dữ liệu (phải có quyền REPLICATION).
* -D /data/: Thư mục lưu trữ dữ liệu sao lưu trên máy chủ đích.
* -Fp: Sao lưu dữ liệu ở định dạng Plain (dữ liệu sao chép trực tiếp).
* -Xs: Sao lưu các tệp WAL (Write-Ahead Logs) để phục hồi hoặc tiếp tục sao chép.
* -R: Tạo replication slot và các tệp cấu hình cần thiết để máy chủ đích hoạt động như replica (bao gồm tệp recovery.conf).

# Create replica database
```sh
docker run -it --rm --name postgres-2 `
--net postgres `
-e POSTGRES_USER=postgresadmin `
-e POSTGRES_PASSWORD=admin123 `
-e POSTGRES_DB=postgresdb `
-e PGDATA="/data" `
-v ${PWD}/postgres-2/pgdata:/data `
-v ${PWD}/postgres-2/config:/config `
-v ${PWD}/postgres-2/archive:/mnt/server/archive `
-p 5001:5432 `
postgres:15.0 -c 'config_file=/config/postgresql.conf'
```

# Check table customers in replica database
```sh
docker exec -it postgres-2 bash
```

# Login with replica user
```sh
psql --username=postgresadmin postgresdb

\dt
```
