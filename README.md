# ⚙️ TaskFlow — Backend (Spring Boot)

A lightweight REST API for managing tasks, built with **Java & Spring Boot**, using an in-memory data store.

🔗 **Live API:** [https://task-management-be-dt3g.onrender.com](#)
🔗 **Frontend Repo:** [https://github.com/itsShubhamGupta/task-management-fe/tree/master](#)

---

## ✨ Features

- ✅ RESTful CRUD APIs for tasks
- 📦 In-memory storage — no DB setup required
- 🌱 Pre-loaded with sample tasks on startup
- ⚠️ Request validation with proper error responses
- 🌐 CORS-enabled for frontend integration

## 🛠️ Tech Stack

`Java` `Spring Boot` `Spring Web` `Maven` `JUnit`

## 📡 API Endpoints

| Method | Endpoint                  | Description           |
|--------|----------------------------|------------------------|
| GET    | `/api/tasks`               | Get all tasks          |
| POST   | `/api/tasks`                | Create a new task      |
| PUT    | `/api/tasks/{id}/status`   | Update task status     |
| DELETE | `/api/tasks/{id}`          | Delete a task          |

### Sample Request — Create Task
```json
POST /api/tasks
{
  "name": "Finish report",
  "description": "Complete Q3 summary",
  "status": "PENDING"
}
```

## 🚀 Getting Started

```bash
git clone https://github.com/<your-username>/taskflow-backend.git
cd taskflow-backend
mvn spring-boot:run
```
API runs at `http://localhost:8080`

## 📁 Project Structure

```
src/main/java/com/taskflow/
├── controller/
│   └── TaskController.java
├── service/
│   └── TaskService.java
├── model/
│   └── Task.java
├── exception/
│   └── GlobalExceptionHandler.java
```

## 🧪 Running Tests

```bash
mvn test
```

---

⭐ If you find this useful, consider giving it a star!
