# Project structure:
```
├── .env
├── .gitignore
├── babel.config.js
├── docker-compose.yml
├── Dockerfile
├── jsconfig.json
├── package.json
├── public
├── README.md
├── src
│   ├── ...
├── tsconfig.json
└── vue.config.js
```

[_compose.yaml_](compose.yaml)
```
version: '3.9'

services:
    vue:
        build: .
        ports:
            - "8080:8080"
        volumes:
            - ./src:/vue/src
            - ./package.json:/vue/package.json


```
## Deploy with docker file
```
docker build -t my-web-app .
docker run -p 8080:8080 my-web-app
```
## Deploy with docker compose

```
docker compose up -d
```