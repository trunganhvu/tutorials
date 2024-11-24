# Run a simple PostgreSQL database (docker)
```sh
docker run --name dev-postgres -e POSTGRES_USER=postgresadmin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```
# Run PostgreSQL with persist (save) container data.
```sh
docker run -it --rm --name postgres `
  -e POSTGRES_USER=postgresadmin `
  -e POSTGRES_PASSWORD=admin123 `
  -e PGDATA="/data" `
  -v ${PWD}/pgdata:/var/lib/postgresql/data `
  -p 5000:5432 `
  postgres:15.0
```
* 5000: local port
* 5432: container port
* folder pgdata được tạo trong vị trí chạy terminal (source từ folder trong container)
* -rm: remove container sau ngay khi stop

## docker-compose.yaml
```yaml
version: '3.1'
services:
  db:
    image: postgres:15.0
    restart: always
    environment:
      POSTGRES_USER: postgresadmin
      POSTGRES_PASSWORD: admin123
    ports:
    - 5000:5432
    volumes:
    - ./pgdata:/var/lib/postgresql/data
  adminer:
    image: adminer
    restart: always
    ports:
      - 8080:8080
```

