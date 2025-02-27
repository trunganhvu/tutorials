# vuejs

## Project setup
```
yarn install
```

### Compiles and hot-reloads for development
```
yarn serve
```

### Compiles and minifies for production
```
yarn build
```

### Lints and fixes files
```
yarn lint
```

### Project structure:
```
├── .gitignore
├── babel.config.js
├── compose.yaml
├── Dockerfile
├── node_modules
├── output.jpg
├── package.json
├── public
│   ├── favicon.ico
│   └── index.html
├── README.md
├── src
│   ├── App.vue
│   ├── assets
│   │   └── logo.png
│   ├── components
│   │   └── HelloWorld.vue
│   └── main.js
└── yarn.lock
```

[_compose.yaml_](compose.yaml)
```
services:
  web:
    build:
      context: .
      target: development
    ports:
      - 8080:8080
    volumes:
      - ./:/project
      - /project/node_modules

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