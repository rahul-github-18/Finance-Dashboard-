# Finance Data Processing and Access Control Backend

## Overview

This project is a **Spring Boot backend application** that provides APIs for managing financial records and generating dashboard analytics with **Role-Based Access Control (RBAC)**.

The system allows different types of users to interact with financial data depending on their assigned role. It supports **authentication using JWT**, secure access to APIs, and aggregated financial insights for dashboard visualization.

The application demonstrates **backend architecture design, secure API development, data modeling, validation, and proper separation of concerns**.

---

# Technology Stack

| Component         | Technology                  |
| ----------------- | --------------------------- |
| Backend Framework | Spring Boot                 |
| Language          | Java 21                     |
| Security          | Spring Security + JWT       |
| Database          | PostgreSQL                  |
| ORM               | Spring Data JPA / Hibernate |
| Build Tool        | Gradle                      |
| API Testing       | Postman                     |

---

# System Architecture

The project follows a **layered architecture** to maintain separation of concerns.

```
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database
```

### Controller Layer

Handles HTTP requests and responses.

### Service Layer

Contains business logic and validation.

### Repository Layer

Handles database operations using JPA.

### Security Layer

Manages authentication, JWT validation, and role-based authorization.

---

# Project Structure

```
src/main/java/com/finance/finance_dashboard

├── controller
│   ├── AuthController
│   ├── UserController
│   ├── RecordController
│   └── DashboardController
│
├── service
│   ├── AuthService
│   ├── UserService
│   ├── RecordService
│   └── DashboardService
│
├── repository
│   ├── UserRepository
│   ├── RoleRepository
│   └── FinancialRecordRepository
│
├── entity
│   ├── User
│   ├── Role
│   └── FinancialRecord
│
├── security
│   ├── SecurityConfig
│   ├── JwtService
│   └── JwtFilter
│
└── exception
    └── GlobalExceptionHandler
```

---

# Database Design

## Users Table

| Column   | Type    | Description         |
| -------- | ------- | ------------------- |
| id       | BIGINT  | Primary key         |
| name     | VARCHAR | User name           |
| email    | VARCHAR | Unique email        |
| password | VARCHAR | Encrypted password  |
| active   | BOOLEAN | Account status      |
| role_id  | BIGINT  | Foreign key to Role |

---

## Roles Table

| id | name    |
| -- | ------- |
| 1  | ANALYST |
| 2  | VIEWER  |
| 3  | ADMIN   |

Roles determine API access permissions.

---

## Financial Records Table

| Column      | Type    | Description        |
| ----------- | ------- | ------------------ |
| id          | BIGINT  | Primary key        |
| amount      | DOUBLE  | Transaction amount |
| type        | VARCHAR | income / expense   |
| category    | VARCHAR | Expense category   |
| date        | DATE    | Transaction date   |
| description | VARCHAR | Notes              |

---

# Role-Based Access Control

The system enforces permissions based on roles.

| Role    | Permissions                             |
| ------- | --------------------------------------- |
| ADMIN   | Full access (users, records, dashboard) |
| ANALYST | Access records and dashboard analytics  |
| VIEWER  | Access dashboard data only              |

Security is implemented using **Spring Security filters and JWT tokens**.

---

# Authentication Flow

1. User registers or exists in the system.
2. User logs in using email and password.
3. Server validates credentials.
4. A **JWT token** is generated and returned.
5. The client includes the token in the request header:

```
Authorization: Bearer <JWT_TOKEN>
```

6. The JWT filter validates the token and extracts the user role.
7. Spring Security authorizes the request.

---

# API Endpoints

## Authentication APIs

### Register User

POST `/api/auth/register`

Request:

```
{
 "name": "Rahul",
 "email": "rahul@gmail.com",
 "password": "123456"
}
```

---

### Login

POST `/api/auth/login`

Response:

```
JWT_TOKEN
```

---

# User Management APIs

### Create User

POST `/api/users`

### Get All Users

GET `/api/users`

### Get User By ID

GET `/api/users/{id}`

### Update User

PUT `/api/users/{id}`

### Delete User

DELETE `/api/users/{id}`

### Activate / Deactivate User

PATCH `/api/users/{id}/status`

---

# Financial Records APIs

### Create Record

POST `/api/records`

```
{
 "amount":1000,
 "type":"expense",
 "category":"Food",
 "date":"2026-04-05",
 "description":"Lunch"
}
```

---

### Get All Records

GET `/api/records`

Supports pagination and filtering.

Example:

```
/api/records?page=0&size=10
/api/records?category=Food
/api/records?type=expense
```

---

### Update Record

PUT `/api/records/{id}`

---

### Delete Record

DELETE `/api/records/{id}`

---

# Dashboard APIs

These endpoints provide aggregated analytics.

### Category Summary

GET `/api/dashboard/category-summary`

Example response:

```
{
 "Food": 2000,
 "Transport": 500
}
```

---

### Monthly Trends

GET `/api/dashboard/monthly-trends`

Example response:

```
{
 "JANUARY": 1500,
 "FEBRUARY": 2200
}
```

---

### Total Income

GET `/api/dashboard/total-income`

### Total Expense

GET `/api/dashboard/total-expense`

### Net Balance

GET `/api/dashboard/net-balance`

---

# Validation and Error Handling

Input validation ensures data consistency.

Example validation rules:

* Amount must be positive
* Category cannot be empty
* Email must be unique

Invalid inputs return meaningful error messages using a **global exception handler**.

Example error:

```
400 Bad Request
Email already exists
```

---

# Security Implementation

Security is implemented using:

* Spring Security
* JWT authentication
* Role-based authorization
* Secure password hashing using BCrypt

---

# Running the Project

## Prerequisites

* Java 21
* PostgreSQL
* Gradle

---

## Database Setup

Create database:

```
CREATE DATABASE finance_dashboard;
```

Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/finance_dashboard
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

---

## Run Application

```
./gradlew bootRun
```

Server runs on:

```
http://localhost:8080
```

---

# Testing APIs

APIs can be tested using **Postman**.

Workflow:

1. Register user
2. Login to obtain JWT token
3. Add token to request headers
4. Access secured endpoints

---

# Assumptions

* Users must have a role assigned.
* Email addresses are unique.
* JWT tokens expire after a configured time.
* Only authorized roles can perform certain operations.

---

# Tradeoffs and Design Choices

* JWT authentication was chosen for stateless API security.
* Spring Data JPA simplifies database interactions.
* Role-based access ensures controlled data access.
* Passwords are encrypted using BCrypt for security.

---

# Future Improvements

Potential improvements include:

* API documentation using Swagger
* Unit and integration tests
* Rate limiting
* Pagination for all endpoints
* DTO pattern for secure responses
* Docker containerization

---

# Conclusion

This project demonstrates the implementation of a **secure, role-based financial dashboard backend**.
It emphasizes **clean architecture, security best practices, database design, and API development**, aligning with the evaluation criteria of backend design, functionality, validation, and maintainability.
