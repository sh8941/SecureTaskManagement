# 🔐 SecureTaskManagement API

A **Spring Boot backend project** that demonstrates secure task management with authentication, authorization, role-based access control, and audit logging.

This project simulates a **team task management platform** where users can:

* Create and manage tasks
* Assign tasks to other users
* Collaborate through comments
* Track activities securely via audit logs

The system is designed to showcase **enterprise-level backend architecture** using Spring Boot and Spring Security.

---

## 🚀 Features

### 🔐 Security & Access Control

* JWT-based Authentication
* Role-Based Access Control (RBAC)
* Method-level security using `@PreAuthorize`
* Fine-grained authorization (ownership & assignment based access)

---

### 📋 Task Management

* Task creation, update, and deletion (Soft Delete)
* Task assignment to users
* Task priority & workflow status
* Task visibility control (public/private)

---

### 💬 Collaboration

* Add comments to tasks
* Secure comment visibility based on access rules

---

### 📊 Audit & Tracking

* Audit logging for user actions
* Tracks entity changes for security and traceability

---

### ⚙️ Backend Design

* RESTful API architecture
* DTO-based request/response handling
* Centralized exception handling
* Input validation using Jakarta Validation
* Clean layered architecture

---

## 🛠 Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Security**
* **JWT Authentication**
* **Spring Data JPA**
* **Hibernate**
* **MySQL / H2**
* **MapStruct (for DTO mapping)**
* **Maven**

---

## 📁 Project Folder Structure

```bash
SecureTaskManagement
│
├── src/main/java/com/securetaskmanagement
│
│   ├── config/                # Security & application configs
│   │       SecurityConfig.java
│   │       JwtFilter.java
│   │       JwtService.java
│
│   ├── controller/            # REST Controllers
│   │       AuthController.java
│   │       TaskController.java
│   │       CommentController.java
│
│   ├── service/               # Business logic interfaces
│   │       AuthService.java
│   │       TaskService.java
│   │       CommentService.java
│
│   ├── service/impl/          # Service implementations
│
│   ├── repository/            # Data access layer
│   │       UserRepository.java
│   │       TaskRepository.java
│   │       CommentRepository.java
│   │       RoleRepository.java
│
│   ├── entity/                # JPA entities
│   │       User.java
│   │       Role.java
│   │       Task.java
│       TaskComment.java
│       AuditLog.java
│
│   ├── dto/                   # Request & Response DTOs
│       TaskRequest.java
│       TaskResponse.java
│       AuthRequest.java
│       AuthResponse.java
│
│   ├── mapper/                # MapStruct mappers
│
│   ├── authorization/         # Custom authorization logic
│
│   ├── exception/             # Global exception handling
│       GlobalExceptionHandler.java
│
│   ├── util/                  # Utility classes
│
│   └── SecureTaskManagementApplication.java
│
└── src/main/resources
        application.properties
```

---

## 🗄 Database Schema

### USERS

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| username   | VARCHAR   |
| email      | VARCHAR   |
| password   | VARCHAR   |
| role_id    | BIGINT    |
| created_at | TIMESTAMP |

---

### ROLES

| Column    | Type    |
| --------- | ------- |
| id        | BIGINT  |
| role_name | VARCHAR |

**Example Roles:**

* ADMIN
* MANAGER
* USER

---

### TASKS

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| title       | VARCHAR   |
| description | TEXT      |
| priority    | VARCHAR   |
| status      | VARCHAR   |
| visibility  | VARCHAR   |
| due_date    | TIMESTAMP |
| created_by  | BIGINT    |
| assigned_to | BIGINT    |
| created_at  | TIMESTAMP |
| updated_at  | TIMESTAMP |
| deleted     | BOOLEAN   |

---

### TASK_COMMENTS

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| task_id    | BIGINT    |
| user_id    | BIGINT    |
| comment    | TEXT      |
| created_at | TIMESTAMP |

---

### AUDIT_LOGS

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| user_id     | BIGINT    |
| action      | VARCHAR   |
| entity_type | VARCHAR   |
| entity_id   | BIGINT    |
| timestamp   | TIMESTAMP |

---

## 🔗 API Endpoints

### 🔐 Authentication

#### Register User

```http
POST /api/auth/register
```

**Request**

```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "password"
}
```

---

#### Login

```http
POST /api/auth/login
```

**Request**

```json
{
  "username": "john",
  "password": "password"
}
```

**Response**

```json
{
  "token": "JWT_TOKEN"
}
```

---

## 📌 Task APIs

* **Create Task** → `POST /api/tasks`
* **Get All Tasks** → `GET /api/tasks`
* **Get Task By ID** → `GET /api/tasks/{id}`
* **Update Task** → `PUT /api/tasks/{id}`
* **Delete Task (Soft Delete)** → `DELETE /api/tasks/{id}`
* **Assign Task** → `POST /api/tasks/{taskId}/assign/{userId}`

---

## 💬 Comment APIs

* **Add Comment** → `POST /api/tasks/{taskId}/comments`
* **Get Task Comments** → `GET /api/tasks/{taskId}/comments`

---

## 🔐 Security

This project uses **Spring Security with JWT authentication**.

### Security Features:

* Stateless Authentication
* Role-Based Authorization
* Method-Level Security (`@PreAuthorize`)
* Protected REST APIs

**Example:**

```java
@PreAuthorize("hasRole('ADMIN')")
```

---

## 📊 Future Improvements

* Task file attachments
* Email notifications
* WebSocket real-time updates
* Pagination & advanced filtering
* Task analytics dashboard

---

## 🎯 Learning Goals

This project demonstrates:

* Spring Security & JWT Authentication
* REST API Design
* Database relationships (JPA/Hibernate)
* Secure backend architecture
* Role-based authorization
* Clean layered architecture

---

## 📄 License

This project is created for **learning and portfolio purposes**.

---

## ⭐ Why This Project Stands Out

* Implements **real-world authorization logic (not just CRUD)**
* Clean and scalable backend architecture
* Strong focus on **security and validation**
* Demonstrates **industry-relevant backend practices**
