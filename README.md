# Blog Application REST API

A modern Spring Boot REST API for a blog platform with user management, posts, and comments functionality.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Error Handling](#error-handling)
- [Project Structure](#project-structure)
- [Contributing](#contributing)

## ✨ Features

- **User Management**: Create, read, update, and delete users
- **Blog Posts**: Full CRUD operations for blog posts
- **Comments System**: Add and manage comments on posts
- **Robust Error Handling**: Custom exceptions with proper HTTP status codes
- **RESTful API**: Clean, consistent API design
- **Data Transfer Objects**: Proper separation between entities and API responses
- **PostgreSQL Integration**: Persistent data storage
- **Automatic Timestamps**: Creation and update time tracking

## 🛠 Technology Stack

- **Java 21** - Programming language
- **Spring Boot 3.5.0** - Application framework
- **Spring Data JPA** - Data persistence layer
- **PostgreSQL** - Database
- **Maven** - Dependency management and build tool
- **Jackson** - JSON serialization/deserialization

### Dependencies

```xml
<!-- Core Spring Boot -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- PostgreSQL Driver -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

## 🏗 Architecture

The application follows a layered architecture pattern:

```
┌─────────────────┐
│   Controllers   │ ← REST endpoints
├─────────────────┤
│    Services     │ ← Business logic
├─────────────────┤
│  Repositories   │ ← Data access
├─────────────────┤
│   Entities      │ ← Database models
└─────────────────┘
```

**Key Components:**

- **Controllers**: Handle HTTP requests and responses
- **Services**: Contain business logic and validation
- **Repositories**: Database operations using Spring Data JPA
- **DTOs**: Data transfer objects for API communication
- **Mappers**: Convert between entities and DTOs
- **Exception Handlers**: Global error handling

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL 12+

### Installation

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd blogApp
   ```

2. **Set up PostgreSQL database**

   ```sql
   CREATE DATABASE blogdb;
   CREATE USER postgres WITH PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE blogdb TO postgres;
   ```

3. **Configure database connection**

   Update `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

4. **Build and run the application**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Verify installation**

   The API will be available at `http://localhost:8080`

## 📚 API Endpoints

### Posts

| Method   | Endpoint                 | Description     | Request Body | Response         |
| -------- | ------------------------ | --------------- | ------------ | ---------------- |
| `GET`    | `/api/posts`             | Get all posts   | -            | `List<PostDTO>`  |
| `GET`    | `/api/posts/{id}`        | Get post by ID  | -            | `PostDTO`        |
| `POST`   | `/api/posts`             | Create new post | `PostDTO`    | `PostDTO`        |
| `PUT`    | `/api/posts/update/{id}` | Update post     | `PostDTO`    | `PostDTO`        |
| `DELETE` | `/api/posts/delete/{id}` | Delete post     | -            | `204 No Content` |

### Users

| Method   | Endpoint         | Description     | Request Body | Response        |
| -------- | ---------------- | --------------- | ------------ | --------------- |
| `GET`    | `/api/user`      | Get all users   | -            | `List<UserDTO>` |
| `GET`    | `/api/user/{id}` | Get user by ID  | -            | `UserDTO`       |
| `POST`   | `/api/user`      | Create new user | `User`       | `String`        |
| `PUT`    | `/api/user/{id}` | Update user     | `User`       | `User`          |
| `DELETE` | `/api/user/{id}` | Delete user     | -            | `String`        |

### Comments

| Method   | Endpoint                                   | Description           | Request Body | Response           |
| -------- | ------------------------------------------ | --------------------- | ------------ | ------------------ |
| `GET`    | `/api/posts/{postId}/comments`             | Get comments for post | -            | `List<CommentDTO>` |
| `POST`   | `/api/posts/{postId}/comments`             | Add comment to post   | `CommentDTO` | `String`           |
| `DELETE` | `/api/posts/{postId}/comments/{commentId}` | Delete comment        | -            | `String`           |

### Example Requests

**Create a User:**

```bash
curl -X POST http://localhost:8080/api/user \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "bio": "Software developer and blogger"
  }'
```

**Create a Post:**

```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post..."
  }'
```

## 🗄 Database Schema

### Users Table (`appUsers`)

```sql
id          INTEGER PRIMARY KEY
username    VARCHAR
bio         VARCHAR
```

### Posts Table (`posts`)

```sql
id          BIGINT PRIMARY KEY
title       VARCHAR(500) NOT NULL
content     TEXT NOT NULL
created_at  TIMESTAMP
updated_at  TIMESTAMP
user_id     INTEGER FK → appUsers(id)
```

### Comments Table

```sql
id          INTEGER PRIMARY KEY
content     TEXT NOT NULL
created_at  TIMESTAMP
user_id     INTEGER FK → appUsers(id)
post_id     BIGINT FK → posts(id)
```

## ⚠️ Error Handling

The application uses a robust error handling system with custom exceptions:

### Custom Exceptions

- `PostNotFoundException` - When a post is not found
- `UserNotFoundException` - When a user is not found
- `BlogAppException` - General application errors

### Error Response Format

```json
{
  "message": "Post not found with id: 123",
  "error": "Post Not Found",
  "status": 404,
  "path": "/api/posts/123",
  "timestamp": "2025-07-07 20:17:45"
}
```

### HTTP Status Codes

- `200 OK` - Successful GET, PUT operations
- `201 Created` - Successful POST operations
- `204 No Content` - Successful DELETE operations
- `404 Not Found` - Resource not found
- `400 Bad Request` - Invalid request data
- `500 Internal Server Error` - Unexpected server errors

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/example/blogApp/
│   │   ├── BlogAppApplication.java          # Main application class
│   │   ├── controller/                      # REST controllers
│   │   │   ├── PostController.java
│   │   │   ├── UserController.java
│   │   │   └── CommentController.java
│   │   ├── service/                         # Business logic
│   │   │   ├── PostService.java
│   │   │   ├── PostServiceImpl.java
│   │   │   ├── UserService.java
│   │   │   ├── UserServiceImpl.java
│   │   │   ├── CommentService.java
│   │   │   └── CommentServiceImpl.java
│   │   ├── repository/                      # Data access layer
│   │   │   ├── PostRepository.java
│   │   │   ├── UserRepository.java
│   │   │   └── CommentRepository.java
│   │   ├── models/                          # JPA entities
│   │   │   ├── Post.java
│   │   │   ├── User.java
│   │   │   └── Comment.java
│   │   ├── dto/                            # Data transfer objects
│   │   │   ├── PostDTO.java
│   │   │   ├── UserDTO.java
│   │   │   ├── CommentDTO.java
│   │   │   └── ErrorResponse.java
│   │   ├── util/                           # Utility classes
│   │   │   ├── PostMapper.java
│   │   │   ├── UserMapper.java
│   │   │   └── CommentMapper.java
│   │   └── exception/                      # Custom exceptions
│   │       ├── PostNotFoundException.java
│   │       ├── UserNotFoundException.java
│   │       ├── BlogAppException.java
│   │       └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.properties          # Configuration
└── test/                                   # Test classes
```

## 🔧 Configuration

### Application Properties

```properties
# Application name
spring.application.name=blogApp

# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=postgres
spring.datasource.password=your_password

# JPA/Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Environment Variables (Recommended)

For production deployment, use environment variables:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/blogdb
export DB_USERNAME=postgres
export DB_PASSWORD=your_secure_password
```

## 🧪 Testing

Run tests with Maven:

```bash
mvn test
```

## 🚀 Deployment

### Local Development

```bash
mvn spring-boot:run
```

### Production Build

```bash
mvn clean package
java -jar target/blogApp-0.0.1-SNAPSHOT.jar
```

## 📝 Future Enhancements

- [ ] Authentication and authorization (JWT)
- [ ] Input validation with Bean Validation
- [ ] Pagination for list endpoints
- [ ] API documentation with Swagger/OpenAPI
- [ ] Unit and integration tests
- [ ] Docker containerization
- [ ] Caching with Redis
- [ ] File upload for user avatars
- [ ] Email notifications
- [ ] Search functionality

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Your Name** - _Initial work_ - [YourGitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- PostgreSQL community for the robust database
- All contributors who have helped this project

---

**Note**: This is a learning/demonstration project. For production use, please implement proper security measures, input validation, and comprehensive testing.
