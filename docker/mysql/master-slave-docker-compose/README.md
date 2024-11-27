# Create network
```sh
docker network create database-group-net
```
https://dev.to/siddhantkcode/how-to-set-up-a-mysql-master-slave-replication-in-docker-4n0a
# Pull mysql
```sh
docker pull mysql/mysql-server:5.7
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

<!-- ```sh
CREATE USER 'repl_user'@'%' IDENTIFIED WITH mysql_native_password BY 'repl_password';
GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'repl_user'@'%';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'repl_user'@'%';
``` -->
```sh
CREATE USER 'replication_user'@'%' IDENTIFIED WITH mysql_native_password BY 'replication_password';
CREATE USER 'repl_user'@'%' IDENTIFIED WITH mysql_native_password BY 'repl_password';

GRANT REPLICATION SLAVE ON *.* TO 'replication_user'@'%';
FLUSH PRIVILEGES;
SHOW MASTER STATUS;
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