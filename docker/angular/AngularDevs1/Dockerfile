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