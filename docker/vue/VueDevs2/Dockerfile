# Base image: Use Node.js 16 as the foundation
FROM node:16

# Install Vue CLI globally
# Version 4.5.19 specified to ensure compatibility
RUN npm install -g @vue/cli@~4.5.19

# Set the working directory inside the container
WORKDIR /vue

# Copy package.json first to leverage Docker cache
# This layer will only rebuild if package.json changes
COPY package.json ./

# Install all dependencies defined in package.json
RUN npm install

# Copy the rest of the application code
# This includes source files, configuration files, etc.
COPY ./ ./

# Document that the container listens on port 8080
EXPOSE 8080

# Define the command to run when the container starts
# 'npm run serve' starts the Vue development server
ENTRYPOINT npm run serve