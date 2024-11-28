# MySQL container
```sh
# Create network
docker network create my-network

# Container
docker run -d -p 3306:3306 --name mysql-container --network=my-network -e MYSQL_ROOT_PASSWORD=password mysql:latest

# Access to container
docker exec -it mysql-container bash

# Login root
mysql -u root -p
show databases;
use mydatabase;
```