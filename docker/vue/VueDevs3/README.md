# Dev env
## Execute 
```sh
docker compose -f dev-docker-compose.yaml up -d

# OR
docker build -f Dev.Dockerfile -t my-web-app .
docker run -p 8080:8080 my-web-app
```

### dev-docker-compose.yaml
```yaml
version: '3.9'

services:
# Local
  app-vue-front:
    container_name: app-vue-front
    image: vuejs_docker:dev
    build:
      context: .
      dockerfile: Dev.Dockerfile
    volumes:
      - '.:/app'
      - '/app/node_modules'
    ports:
      - '8080:8080'
```

### Dev.Dockerfile
```yml
FROM node:18.12.1-alpine

WORKDIR /app
COPY . ./
RUN npm install

EXPOSE 8080 8080

CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]
```

# Prod env
## Execute 
```sh
docker compose -f prod-docker-compose.yaml up -d

# OR
docker build -f Prod.Dockerfile -t my-web-app .
docker run -p 80:80 my-web-app
```

### prod-docker-compose.yaml
```yaml
version: '3.9'

services:
# Prod
  app-vue-prod:
    container_name: app-vue-prod
    image: vuejs_docker:prod
    build:
      context: .
      dockerfile: Prod.Dockerfile
    volumes:
      - '.:/app'
      - '/app/node_modules'
    ports:
      - '80:80'
```

### Prod.Dockerfile
```yml
# develop stage
FROM node:18.12.1-alpine as develop-stage
WORKDIR /app

# Copy the package.json and install dependencies
COPY package*.json ./
RUN npm install

# Copy rest of the files
COPY . .

# Build the project
RUN npm run build


FROM nginx:alpine as production-build
COPY ./nginx.conf /etc/nginx/nginx.conf

## Remove default nginx index page
RUN rm -rf /usr/share/nginx/html/*

# Copy from the stahg 1
COPY --from=develop-stage /app/dist /usr/share/nginx/html

EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
```