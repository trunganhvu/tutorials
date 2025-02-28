# Dev env
## Execute 
```sh
docker compose -f docker-compose.yml up -d

# OR
docker build -f Dockerfile -t my-web-app .
docker run -p 8080:4200 my-web-app
```

### docker-compose.yaml
```yaml
version: '3.9'

services:
  angular-app:
    container_name: angular-dev
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:4200"
    volumes:
      # Mount source code for live development
      - ./:/usr/src/app
      # Use named volume for node_modules to prevent overwriting with host directory
      - node_modules:/usr/src/app/node_modules
    environment:
      - NODE_ENV=development
    # Keep the container running even on failure
    restart: unless-stopped

volumes:
  node_modules:
```

### Dev.Dockerfile
```yml
# Use Node.js 20 with Alpine Linux as the base image (lightweight)
FROM node:20-alpine

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy all files from the host to the container's working directory
COPY . /usr/src/app

# Install Angular CLI globally to provide the 'ng' command
RUN npm install -g @angular/cli

# Install all dependencies defined in package.json
RUN npm install

# Expose port 4200 - the default port for Angular applications
EXPOSE 4200

# Command to start the Angular development server
# --host 0.0.0.0 allows connections from outside the container
CMD ["ng", "serve", "--host", "0.0.0.0"]
```
