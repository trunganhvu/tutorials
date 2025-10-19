# JHipster Monolithic Application

This is a JHipster-generated monolithic application with a Java/Spring Boot backend and a modern frontend.

## Quick Start

### Prerequisites

- Java 17+
- Node.js 18+
- Maven
- Docker (optional, for database and other services)

### Development Setup

1. **Install Dependencies**
   ```bash
   # Install Node.js dependencies
   ./npmw install
   
   # Install Maven dependencies
   ./mvnw clean install
   ```

2. **Run the Application**
   In separate terminals:
   ```bash
   # Start the backend
   ./mvnw
   
   # Start the frontend (in another terminal)
   ./npmw start
   ```
   - Backend: http://localhost:8080/
   - Frontend: http://localhost:9000/
   - API Documentation: http://localhost:8080/swagger-ui/

### Building for Production

```bash
# Build with production profile
./mvnw -Pprod clean verify

# Run the production JAR
java -jar target/*.jar
```

## Configuration

- Main configuration: `src/main/resources/config/application.yml`
- Production profile: `application-prod.yml`
- Development profile: `application-dev.yml`
- Test profile: `application-test.yml`

## Docker Support

Run with Docker Compose:
```bash
docker-compose -f src/main/docker/app.yml up -d
```
