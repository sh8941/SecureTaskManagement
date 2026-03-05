# 🔐 Secure Task Management API

A **Spring Boot backend project** that demonstrates secure task management with authentication, authorization, role-based access control, and audit logging.

This project simulates a **team task management platform** where users can create tasks, assign them to others, collaborate through comments, and track activities securely.

The system is designed to showcase **enterprise backend architecture** using Spring Boot and Spring Security.

---

# 🚀 Features

* User Authentication (JWT)
* Role-Based Access Control (RBAC)
* Task Creation & Assignment
* Task Priority & Workflow Status
* Task Comments (Collaboration)
* Audit Logging for Security Tracking
* Soft Delete Support
* RESTful API Architecture
* Database Integration (MySQL / H2)

---

# 🛠 Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* MySQL / H2
* Maven

---

# 📁 Project Folder Structure

```
secure-task-management
│
├── src/main/java/com/example/taskmanagement
│
│   ├── config
│   │       SecurityConfig.java
│   │       JwtFilter.java
│   │       JwtService.java
│   │
│   ├── controller
│   │       AuthController.java
│   │       TaskController.java
│   │       CommentController.java
│   │
│   ├── service
│   │       AuthService.java
│   │       TaskService.java
│   │       CommentService.java
│   │
│   ├── repository
│   │       UserRepository.java
│   │       TaskRepository.java
│   │       CommentRepository.java
│   │       RoleRepository.java
│   │
│   ├── entity
│   │       User.java
│   │       Role.java
│   │       Task.java
│   │       TaskComment.java
│   │       AuditLog.java
│   │
│   ├── dto
│   │       TaskRequest.java
│   │       TaskResponse.java
│   │       AuthRequest.java
│   │       AuthResponse.java
│   │
│   ├── exception
│   │       GlobalExceptionHandler.java
│   │
│   └── SecureTaskManagementApplication.java
│
└── resources
        application.properties
```

---

# 🗄 Database Schema

## USERS

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| username   | VARCHAR   |
| email      | VARCHAR   |
| password   | VARCHAR   |
| role_id    | BIGINT    |
| created_at | TIMESTAMP |

---

## ROLES

| Column    | Type    |
| --------- | ------- |
| id        | BIGINT  |
| role_name | VARCHAR |

Example Roles

```
ADMIN
MANAGER
USER
```

---

## TASKS

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

## TASK_COMMENTS

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| task_id    | BIGINT    |
| user_id    | BIGINT    |
| comment    | TEXT      |
| created_at | TIMESTAMP |

---

## AUDIT_LOGS

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| user_id     | BIGINT    |
| action      | VARCHAR   |
| entity_type | VARCHAR   |
| entity_id   | BIGINT    |
| timestamp   | TIMESTAMP |

---

# 🔗 API Endpoints

## Authentication

### Register User

```
POST /api/auth/register
```

Request

```
{
  "username": "john",
  "email": "john@example.com",
  "password": "password"
}
```

---

### Login

```
POST /api/auth/login
```

Request

```
{
  "username": "john",
  "password": "password"
}
```

Response

```
{
  "token": "JWT_TOKEN"
}
```

---

# 📌 Task APIs

## Create Task

```
POST /api/tasks
```

Request

```
{
  "title": "Implement JWT Authentication",
  "description": "Add JWT security to Spring Boot API",
  "priority": "HIGH",
  "dueDate": "2026-03-10"
}
```

---

## Get All Tasks

```
GET /api/tasks
```

---

## Get Task By ID

```
GET /api/tasks/{id}
```

---

## Update Task

```
PUT /api/tasks/{id}
```

---

## Delete Task (Soft Delete)

```
DELETE /api/tasks/{id}
```

---

## Assign Task

```
POST /api/tasks/{taskId}/assign/{userId}
```

---

# 💬 Comment APIs

## Add Comment

```
POST /api/tasks/{taskId}/comments
```

---

## Get Task Comments

```
GET /api/tasks/{taskId}/comments
```

---

# 🔐 Security

This project uses **Spring Security with JWT authentication**.

Security features include:

* Stateless Authentication
* Role-Based Authorization
* Method-Level Security (`@PreAuthorize`)
* Protected REST APIs

Example:

```
@PreAuthorize("hasRole('ADMIN')")
```

---

# 📊 Future Improvements

* Task file attachments
* Email notifications
* WebSocket real-time updates
* Pagination & filtering
* Task analytics dashboard

---

# 🎯 Learning Goals

This project helps understand:

* Spring Security
* JWT Authentication
* REST API design
* Database relationships
* Secure backend architecture
* Role-based authorization

---

# 📄 License

This project is created for **learning and portfolio purposes**.
