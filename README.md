# Project Management Application

This is a full-stack Project Management application built using **Spring Boot** for the backend and **Angular (non-standalone)** for the frontend. It provides user authentication, project creation, task management, and project/task listing features.

---

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [Project Structure](#project-structure)

---

## Features

- User authentication with **JWT**
- Create, read, and manage projects
- Add, update, complete, and delete tasks for projects
- Secure endpoints based on logged-in user
- Responsive and interactive frontend using Angular

---

## Technologies Used

**Backend:**
- Spring Boot
- Spring Security
- Hibernate / JPA
- MySQL / MariaDB
- JWT for authentication

**Frontend:**
- Angular (non-standalone project)
- Angular Reactive Forms
- HttpClient for API calls
- RxJS for state management

---

## Backend Setup

1. **Clone the backend repository** :

```bash
git clone <backend-repo-url>
cd backend
```
2. **Configure the database** :
The backend uses MySQL. Create a database named projectManagement. By default, the application expects a local MySQL server with username root and no password. Tables are automatically created on startup.

3. **Build and run the backend** :
```bash
mvn clean install
mvn spring-boot:run
```
The backend server will run at http://localhost:8085

## Frontend Setup

1. **Clone the frontend repository** :
```bash
git clone <frontend-repo-url>
cd frontend
```
2. **Install dependencies:** :
```bash
npm install
```

3. **Run the frontend application**:
```bash
ng serve
```

The application will be available at http://localhost:4200
. Make sure the backend is running to allow API communication.

## API Endpoints

### Authentication
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Login with email and password, returns a JWT token |

### Projects
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/projects` | Create a new project |
| `GET` | `/api/projects` | List all projects for the logged-in user |
| `GET` | `/api/projects/{id}` | Get a specific project by ID |

### Tasks (per project)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/projects/{projectId}/tasks` | Create a task |
| `GET` | `/api/projects/{projectId}/tasks` | List tasks for a project |
| `PUT` | `/api/projects/{projectId}/tasks/{taskId}/complete` | Mark a task as completed |
| `DELETE` | `/api/projects/{projectId}/tasks/{taskId}` | Delete a task |

## Usage

1. **Register** a new user (if registration is implemented) or use predefined users.
2. **Login** with your email and password.
3. Access the **project dashboard** to create new projects.
4. Open a specific project to manage tasks: **create**, **mark complete**, or **delete**.

> **Note:** All operations are tied to the specific authenticated user. JWT tokens are securely stored in local storage on the frontend to maintain the session.

## Project Structure

### Backend
```text
src/main/java/com/yourpackage/
├── controllers/    # REST controllers
├── entities/       # JPA entities
├── dtos/           # Data transfer objects
├── services/       # Business logic
├── repositories/   # Spring Data JPA repositories
├── security/       # JWT and Spring Security configuration
└── mappers/        # Map entities to DTOs
```
### Frontend

```text
src/app/
├── components/           # Angular components for login, projects, and tasks
├── services/             # HTTP services for API calls
├── models/               # TypeScript interfaces for Project, Task, and Auth
├── app-routing.module.ts # Application routes
└── app.module.ts         # Main Angular module
```
## Frontend Repository

The frontend project is maintained in a separate repository:

[Frontend Repo Link](https://github.com/ZakariaOuakrim/Hahn_Project_Management_Frontend)