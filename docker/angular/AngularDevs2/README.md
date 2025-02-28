# Dev env
## Execute 
```sh
docker compose -f docker-compose.yml up -d

# OR
docker build -f Dockerfile -t my-web-app .
# Port 80 modifiable with changed listened port in nginx.conf
docker run -p 8080:80 my-web-app
```

### docker-compose.yaml
```yaml
version: '3.8'

services:
  web:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:80"

```

### Dev.Dockerfile
```yml

FROM node:20-alpine3.19 AS build

WORKDIR /app

COPY ./src/package*.json ./

RUN npm ci

RUN npm install -g @angular/cli

COPY ./src .

RUN npm run build --configuration=production

FROM nginx:1.24-alpine 

COPY ./nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build /app/dist/src/browser /usr/share/nginx/html

# Mappring with nginx.conf listen 80;
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
```
