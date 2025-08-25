# Deployment Guide

## Local
1. Install Java 17, Maven, MySQL, Node.js.
2. Create DB user and update `application.properties`.
3. `cd backend && mvn spring-boot:run`
4. `cd frontend && npm install && npm run build && npm run preview`

## Cloud (Quick Options)
- **Render/Heroku-like**: Create a Spring Boot service, add environment variables for DB, deploy via Git.
- **AWS**: RDS (MySQL), EC2 for backend, S3/CloudFront for frontend static build.

Expose backend `8080` and point frontend `api.js` to the public backend URL.
