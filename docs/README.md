# ZIDIO CONNECT — Internship & Job Management Portal

Full-stack project with **Spring Boot + MySQL** backend and **React (Vite)** frontend.
Includes auth (JWT), job posting, applying, status tracking, basic admin/recruiter flows.

## Quick Start

### 1) Backend
- Requirements: Java 17, Maven, MySQL
- Edit `backend/src/main/resources/application.properties` with your MySQL password.
- Run:
```bash
cd backend
mvn spring-boot:run
```
- Swagger Docs: `http://localhost:8080/swagger-ui.html`
- Seed users (password = `pass123`):
  - `admin@zidio.in` (ADMIN)
  - `recruiter@zidio.in` (RECRUITER)
  - `student@zidio.in` (STUDENT)

### 2) Frontend
```bash
cd frontend
npm install
npm run start
```
- Open `http://localhost:5173`

Login with any seeded user and test the flows.
