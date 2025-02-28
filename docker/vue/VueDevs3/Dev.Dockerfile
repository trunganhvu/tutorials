FROM node:18.12.1-alpine

WORKDIR /app
COPY . ./
RUN npm install

EXPOSE 8080 8080

CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]