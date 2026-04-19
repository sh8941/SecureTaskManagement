# 🔐 SecureTaskManagement

**SecureTaskManagement** is a production-ready backend application built using Spring Boot that provides a secure and scalable task management system. It demonstrates industry-level practices such as JWT authentication, role-based authorization, rate limiting, caching, filtering, and pagination.

---

## 🚀 Features

* 🔐 **JWT Authentication & Authorization**

    * Secure login/signup using JWT
    * Role-based and resource-level access control

* 👥 **User & Role Management**

    * Role-based permissions (Admin/User)
    * Secure user operations

* ✅ **Task Management**

    * Create, update, delete, and fetch tasks
    * Task visibility and prioritization

* 💬 **Task Comments**

    * Add and manage comments on tasks
    * Ownership-based authorization

* ⚡ **Rate Limiting**

    * Implemented using Redis to prevent abuse

* 🧠 **Caching with Redis**

    * Improves performance and reduces DB load

* 🔍 **Filtering & Pagination**

    * Dynamic filtering using Specifications
    * Paginated API responses

* 📄 **API Documentation**

    * Swagger/OpenAPI integration

* 🐳 **Docker Support**

    * Easy containerized deployment

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot, Spring Security
* **Database:** MySQL
* **Caching & Rate Limiting:** Redis
* **Authentication:** JWT
* **ORM:** Spring Data JPA (Hibernate)
* **Documentation:** Swagger (OpenAPI)
* **Containerization:** Docker

---

## 📁 Project Structure

### 🔐 Authorization

* `AuthorizationService` – Central authorization logic
* `CommentAuthorization` – Access control for comments
* `TaskAuthorization` – Access control for tasks
* `TaskAuthorizationService` – Task-specific authorization

---

### ⚙️ Configuration

* `JwtFilter` – JWT request validation

* `JwtUtil` – Token generation & validation

* `RateLimitFilter` – API rate limiting

* `RedisConfig` – Redis setup

* `SecurityConfig` – Security configuration

* `SecurityUtils` – Utility methods

* `SwaggerConfig` – API documentation setup

* `WebConfig` – Web-related configurations

---

### 🎮 Controllers

* `AuthController` – Authentication APIs
* `UserController` – User management APIs
* `TaskController` – Task operations
* `TaskCommentController` – Comment operations

---

### 📦 DTOs

#### Request

* `AuthRequest`
* `TaskRequestDto`
* `TaskCommentRequest`
* `UserRequestDto`

#### Response

* `AuthResponse`
* `PageResponse`
* `TaskResponseDto`
* `TaskCommentResponseDto`
* `UserResponseDto`

---

### 🗄️ Entities (Database Tables)

* `UserEntity` → **users**
* `RoleEntity` → **roles**
* `TaskEntity` → **tasks**
* `TaskCommentEntity` → **task_comments**

---

### 🔢 Enums

* `Priority`
* `Status`
* `Visibility`

---

### ⚠️ Exception Handling

* `GlobalExceptionHandler` – Centralized error handling
* `ResourceNotFoundException`
* `AccessDeniedException`

---

### 🔄 Mappers

* `UserMapper`
* `TaskMapper`
* `TaskCommentMapper`

---

### ⚡ Rate Limiting

* `RateLimitService` – Handles request throttling using Redis

---

### 🗃️ Repositories

* `UserRepo`
* `RoleRepo`
* `TaskRepo`
* `TaskCommentRepo`

---

### 🧠 Services

* `UserService`
* `TaskService`
* `TaskCommentService`
* `CustomUserDetailsService`

---

### 🔍 Specifications

* `TaskSpecification` – Dynamic filtering logic

---

## 🗃️ Database Schema Overview

### 👤 Users Table

| Column   | Description          |
| -------- | -------------------- |
| id       | Primary key          |
| username | Unique username      |
| email    | User email           |
| password | Encrypted password   |
| role_id  | Foreign key to roles |

### 🛡️ Roles Table

| Column | Description             |
| ------ | ----------------------- |
| id     | Primary key             |
| name   | Role name (ADMIN, USER) |

### ✅ Tasks Table

| Column      | Description                    |
| ----------- | ------------------------------ |
| id          | Primary key                    |
| title       | Task title                     |
| description | Task details                   |
| priority    | Enum (LOW, MEDIUM, HIGH)       |
| status      | Enum (TODO, IN_PROGRESS, DONE) |
| visibility  | Enum (PUBLIC, PRIVATE)         |
| user_id     | Owner of task                  |

### 💬 Task Comments Table

| Column  | Description    |
| ------- | -------------- |
| id      | Primary key    |
| content | Comment text   |
| task_id | Related task   |
| user_id | Comment author |

---

## 📌 Key Highlights

* Clean Architecture & Layered Design
* Secure API using Spring Security & JWT
* Redis-powered caching & rate limiting
* Scalable filtering with JPA Specifications
* Production-ready coding practices

---

## 📖 API Documentation

After running the application, access Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🐳 Run with Docker

```bash
docker-compose up --build
```

---

## 👨‍💻 Author

**Samoon Haider**

---

## 📁 Folder Structure

Below is the high-level structure of the project:

## 📁 Folder Structure

```
src/main/java/com/haider/SecureTaskManagement
│
├── authorization
│   ├── AuthorizationService
│   ├── CommentAuthorization
│   ├── TaskAuthorization
│   └── TaskAuthorizationService
│
├── config
│   ├── JwtFilter
│   ├── JwtUtil
│   ├── RateLimitFilter
│   ├── RedisConfig
│   ├── SecurityConfig
│   ├── SecurityUtils
│   ├── SwaggerConfig
│   └── WebConfig
│
├── controller
│   ├── AuthController
│   ├── TaskController
│   ├── TaskCommentController
│   └── UserController
│
├── dto
│   ├── request
│   │   ├── AuthRequest
│   │   ├── TaskRequestDto
│   │   ├── TaskCommentRequest
│   │   └── UserRequestDto
│   │
│   └── response
│       ├── AuthResponse
│       ├── PageResponse
│       ├── TaskResponseDto
│       ├── TaskCommentResponseDto
│       └── UserResponseDto
│
├── entity
│   ├── UserEntity
│   ├── RoleEntity
│   ├── TaskEntity
│   └── TaskCommentEntity
│
├── enums
│   ├── Priority
│   ├── Status
│   └── Visibility
│
├── exception
│   ├── GlobalExceptionHandler
│   ├── ResourceNotFoundException
│   └── AccessDeniedException
│
├── mapper
│   ├── UserMapper
│   ├── TaskMapper
│   └── TaskCommentMapper
│
├── rateLimit
│   └── RateLimitService
│
├── repo
│   ├── UserRepo
│   ├── RoleRepo
│   ├── TaskRepo
│   └── TaskCommentRepo
│
├── service
│   ├── UserService
│   ├── TaskService
│   ├── TaskCommentService
│   └── CustomUserDetailsService
│
└── specification
    └── TaskSpecification
```


## ⭐ Show Your Support

If you found this project helpful, consider giving it a ⭐ on GitHub!
