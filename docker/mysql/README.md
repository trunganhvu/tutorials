# Create network
```sh
docker network create database-group-net
```

# Pull mysql
```sh
docker pull mysql/mysql-server:5.7
```

# Master container 
```sh
docker run -d --name=master-database -p 33306:3306 --net=database-group-net --hostname=master-database -h h_master `
-e MYSQL_ROOT_HOST=”%” `
-e MYSQL_ROOT_PASSWORD=”password” `
-v /docker/h_master/config/my.cnf:/etc/mysql/my.cnf `
-v /docker/h_master/data:/home/docker/data `
mysql/mysql-server:5.7  `
--server-id=1 `
--log-bin="mysql-bin-1.log" `
--enforce-gtid-consistency="ON" `
--log-slave-updates="ON" `
--gtid-mode="ON" `
--transaction-write-set-extraction="XXHASH64" `
--binlog-checksum="NONE" `
--master-info-repository="TABLE" `
--relay-log-info-repository="TABLE" `
--plugin-load="group_replication.so" `
--relay-log-recovery="ON" `
--loose-group-replication-start-on-boot="OFF" `
--loose-group-replication-group-name="aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee" `
--loose-group-replication-local-address="master-database:6606" `
--loose-group-replication-group-seeds="master-database:6606,slave-database:6606" `
--loose-group-replication-single-primary-mode="ON" `
--loose-group-replication-enforce-update-everywhere-checks="OFF"
```

# Slave container (modifiable)
```sh
docker run -d --name=slave-database -p 33307:3306 --net=database-group-net --hostname=slave-database -h h_slaver `
-e MYSQL_ROOT_HOST=”%” `
-e MYSQL_ROOT_PASSWORD=”password” `
-v /docker/h_slave/config/my.cnf:/etc/mysql/my.cnf `
-v /docker/h_slave/data:/home/docker/data `
mysql/mysql-server:5.7 `
--server-id=2 `
--log-bin="mysql-bin-1.log" `
--enforce-gtid-consistency="ON" `
--log-slave-updates="ON" `
--gtid-mode="ON" `
--transaction-write-set-extraction="XXHASH64" `
--binlog-checksum="NONE" `
--master-info-repository="TABLE" `
--relay-log-info-repository="TABLE" `
--plugin-load="group_replication.so" `
--relay-log-recovery="ON" `
--loose-group-replication-start-on-boot="OFF" `
--loose-group-replication-group-name="aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee" `
--loose-group-replication-local-address="slave-database:6606" `
--loose-group-replication-group-seeds="master-database:6606,slave-database:6606" `
--loose-group-replication-single-primary-mode="ON" `
--loose-group-replication-enforce-update-everywhere-checks="OFF" `
--read-only="ON"
```

# Config master
```sh
docker exec -it master-database bash
mysql -u root -p
show master status\G
```

## Lấy kết quả master mysql-bin-1.000003 và 749
```sh
mysql> show master status\G
*************************** 1. row ***************************
             File: mysql-bin-1.000003
         Position: 749
     Binlog_Do_DB:
 Binlog_Ignore_DB:
Executed_Gtid_Set: 6b9a6412-ab3a-11ef-92d7-0242ac1b0002:1-3
1 row in set (0.00 sec)
```

```sh
CREATE USER 'repl_user'@'%' IDENTIFIED WITH mysql_native_password BY 'repl_password';
GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'repl_user'@'%';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'repl_user'@'%';
```


# Config slave
```sh
docker exec -it slave-database bash
mysql -u root -p
stop slave;
```

## Fill  mysql-bin-1.000003 và 749 từ master
> Check lại MASTER_HOST trong inspect master 
```sh
CHANGE MASTER TO
    MASTER_HOST = '172.27.0.2',
    MASTER_USER = 'repl_user',
    MASTER_PASSWORD = 'repl_password',
    MASTER_LOG_FILE = 'mysql-bin-1.000003',
    MASTER_LOG_POS = 749;
start slave;
show slave status\G
```

# Create database, table master and check slave
## Master create database
```sh
docker exec -it master-database bash
mysql -u root -p
show database;
create database anhvt;
show database;
use anhvt;
create table test(id bigint, name varchar(100));
show tables;
```

## Slave check replica
```sh
docker exec -it master-database bash
mysql -u root -p
show database;
-- database anhvt async from master
use anhvt;
show tables;
```

## Result show databases and tables
```sh
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| anhvt              |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
```

```sh
mysql> show tables;
+-----------------+
| Tables_in_anhvt |
+-----------------+
| test            |
+-----------------+
```