# Spring Boot Gson with Timezone-Aware Date Handling

A Spring Boot application demonstrating advanced Gson integration with automatic timezone conversion for date fields. This implementation provides robust handling of date/time values across different timezones.

## 🌟 Key Features

- **Automatic Timezone Detection**: Automatically detects timezone from request payloads
- **UTC Storage**: All dates are converted to UTC for consistent storage
- **Flexible Input Formats**: Supports multiple date formats with timezone information
- **Thread-safe**: Uses `ThreadLocal` for request-scoped timezone handling
- **Seamless Integration**: Drop-in solution for Spring Boot applications

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Spring Boot 3.5.6
- Maven 3.6.0+

## 🕒 Timezone Handling

### How It Works

1. **Request Processing**:
   - Extracts timezone from request payload (e.g., `timeZone` field)
   - Sets the timezone in `RequestTimeZoneHolder` for the current request
   - Automatically converts incoming dates to UTC

2. **Response Processing**:
   - Converts all dates to ISO-8601 format in UTC
   - Ensures consistent date formatting in API responses

### Supported Date Formats

1. ISO-8601 with timezone offset:
   ```json
   {
     "birthDate": "2025-10-18T15:30:00+07:00",
     "timeZone": "Asia/Ho_Chi_Minh"
   }
   ```

2. Local date-time (uses request timezone):
   ```json
   {
     "birthDate": "2025-10-18T15:30:00",
     "timeZone": "Asia/Ho_Chi_Minh"
   }
   ```

3. Date only (assumes start of day in request timezone):
   ```json
   {
     "birthDate": "2025-10-18",
     "timeZone": "Asia/Ho_Chi_Minh"
   }
   ```

## 🛠️ Configuration

The timezone handling is configured in `GsonConfig.java`:

```java
@Bean
public Gson gson() {
    return new GsonBuilder()
        .registerTypeAdapter(Date.class, new UtcDateAdapter())
        .registerTypeAdapterFactory(new RequestTimeZoneAwareAdapterFactory())
        .setPrettyPrinting()
        .create();
}
```

## 📝 Example Usage

### 1. Create User with Timezone

```http
POST /api/users
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "birthDate": "2025-10-18T15:30:00+07:00",
  "timeZone": "Asia/Ho_Chi_Minh"
}
```
Response:
```json
[
  {
    "name": "John Doe",
    "email": "john@example.com",
    "birthDateUtc": "2025-10-18T08:30:00Z"
  }
]
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/org/anhvt/springbootgson/
│   │   ├── config/               # Configuration classes
│   │   │   ├── GsonConfig.java              # Gson configuration
│   │   │   └── RequestTimeZoneAwareAdapterFactory.java  # Timezone handling
│   │   ├── controller/           # REST controllers
│   │   ├── dto/                  # DTOs for API requests/responses
│   │   ├── model/                # Domain models
│   │   └── adapter/              # Custom Gson type adapters
│   └── resources/
│       └── application.properties
└── test/                         # Test files
```

## 🔄 Timezone Conversion Flow

1. Client sends request with date and timezone
2. `RequestTimeZoneAwareAdapterFactory` captures the timezone
3. `UtcDateAdapter` converts the date to UTC
4. Application processes the date in UTC
5. Response includes dates in ISO-8601 UTC format

### Basic Gson Operations

========== GSON BASIC DEMO ==========
1. Object to JSON:
   {
   "id": 1,
   "full_name": "John Doe",
   "email": "john@example.com",
   "birthDate": "2025-10-18"
   }
2. JSON to Object: John Doe / Sat Oct 18 00:00:00 ICT 2025
3. Pretty JSON:
   {
   "id": 1,
   "full_name": "John Doe",
   "email": "john@example.com",
   "birthDate": "2025-10-18"
   }
4. Email field value: john@example.com
5. List size: 2

### Advanced Gson Features

========== GSON ADVANCED DEMO ==========
1. Only @Expose fields:
   {
   "id": 2,
   "email": "alice@company.com",
   "birthDate": "2025-10-18 21:04:14"
   }
2. Include null fields:
   {
   "id": null,
   "full_name": null,
   "email": "abc@x.com",
   "birthDate": null
   }
3. Field naming policy:
   {
   "Id": 2,
   "full_name": "Alice",
   "Email": "alice@company.com",
   "BirthDate": "18/10/2025"
   }
4. Map JSON:
   {
   "code": 200,
   "message": "OK"
   }
