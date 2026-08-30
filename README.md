# 📚 Library Management System

A full-stack Library Management System built with **Java 17, Spring Boot 3.5.4, Spring Data JPA, MySQL 8.4, HTML/CSS/JavaScript, Bootstrap 5.3.3, Docker, Docker Compose, and Jenkins**. It provides a browser UI and REST API for book CRUD, search, pagination, issuing/returning books, persistence, automated tests, containerized deployment, and CI/CD.

## Executive Summary

The application uses a conventional layered Spring Boot backend behind a static Nginx frontend. MySQL stores library data. Docker Compose runs `mysql`, `library-app`, and `frontend`; Jenkins automates checkout, Maven compilation/testing/packaging, Docker image builds, deployment, and health checks.

```text
Browser → Nginx Frontend → Spring Boot REST API → Service → JPA Repository → MySQL
                                      ↑
                              Jenkins CI/CD
```

## Key Features

- Full book CRUD operations
- Search by title or author
- Client-side pagination (5 books/page)
- Issue and return workflow
- Seven-day due date when a book is issued
- Duplicate title + author protection
- Jakarta validation for title and author
- Centralized exception handling
- Dashboard statistics and backend status
- Responsive Bootstrap UI and toast notifications
- MySQL persistence with Spring Data JPA/Hibernate
- Dockerized backend and Nginx frontend
- Docker Compose orchestration and MySQL health check
- Jenkins CI/CD with Docker and Compose support
- JUnit 5 + Mockito service tests

## Technology Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Backend | Spring Boot 3.5.4 |
| REST | Spring Web |
| Persistence | Spring Data JPA / Hibernate |
| Database | MySQL 8.4 |
| Validation | Jakarta Bean Validation |
| Frontend | HTML5, CSS3, JavaScript |
| UI | Bootstrap 5.3.3 |
| Icons | Font Awesome 6.5.2 |
| Build | Maven |
| Tests | JUnit 5, Mockito |
| Containers | Docker |
| Orchestration | Docker Compose |
| Web server | Nginx Alpine |
| CI/CD | Jenkins |
| VCS | Git / GitHub |

![Java 17](https://img.shields.io/badge/Java-17-orange?logo=openjdk) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen?logo=springboot) ![MySQL](https://img.shields.io/badge/MySQL-8.4-blue?logo=mysql) ![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker) ![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?logo=jenkins) ![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.3-7952B3?logo=bootstrap)

## Architecture

### Repository Structure

```text
LibraryManagementSystem/
├── frontend/
│   ├── css/style.css
│   ├── js/api.js
│   ├── js/app.js
│   ├── index.html
│   ├── books.html
│   ├── add-book.html
│   ├── edit-book.html
│   ├── about.html
│   ├── Dockerfile
│   ├── nginx.conf
│   └── .dockerignore
├── jenkins/
│   └── Dockerfile
├── screenshots/
├── src/
│   ├── main/java/com/library/
│   │   ├── config/DataLoader.java
│   │   ├── config/WebConfig.java
│   │   ├── controller/BookController.java
│   │   ├── entity/Book.java
│   │   ├── exception/BookNotFoundException.java
│   │   ├── exception/GlobalExceptionHandler.java
│   │   ├── repository/BookRepository.java
│   │   ├── service/BookService.java
│   │   └── LibraryManagementSystemApplication.java
│   ├── main/resources/application.properties
│   └── test/java/com/library/service/BookServiceTest.java
├── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
├── pom.xml
├── README.md
└── intern PROJECT REPORT.docx
```

### Backend Responsibilities

| Component | Responsibility |
|---|---|
| `BookController` | HTTP/REST endpoints under `/books` |
| `BookService` | Business rules for books, issuing and returning |
| `BookRepository` | JPA database access |
| `Book` | Entity mapped to `books` |
| `BookNotFoundException` | Missing-book domain exception |
| `GlobalExceptionHandler` | Maps selected exceptions to HTTP responses |
| `DataLoader` | Seeds Clean Code and Effective Java when absent |
| `WebConfig` | Development CORS configuration |

## Data Model

The `Book` entity maps to the `books` table:

| Field | Type | Description |
|---|---|---|
| `id` | `int` | Auto-generated primary key |
| `title` | `String` | Required title |
| `author` | `String` | Required author |
| `status` | `String` | `Available` or `Issued` |
| `borrowerName` | `String` | Current borrower |
| `issueDate` | `LocalDate` | Issue date |
| `dueDate` | `LocalDate` | Seven days after issue |
| `returnDate` | `LocalDate` | Return date |

New books default to `Available`.

## Quickstart

### Prerequisites

For local execution: **Java 17+, Maven, MySQL 8.x, Git, and a modern browser**. For the containerized setup: **Docker Desktop with Docker Compose**.

### Recommended: Docker Compose

From the repository root:

```bash
git clone https://github.com/rohitsalapu00/LibraryManagementSystem.git
cd LibraryManagementSystem
docker compose up --build -d
docker compose ps
```

Open:

- Frontend: `http://localhost/`
- Backend API: `http://localhost:8081/books`
- MySQL: `localhost:3306`

Logs:

```bash
docker compose logs -f library-app
docker compose logs -f frontend
docker compose logs -f mysql
```

Stop:

```bash
docker compose down
```

Remove containers **and database volume**:

```bash
docker compose down -v
```

> `down -v` removes the persistent `mysql-data` volume and therefore deletes its stored database data.

### Local Backend + Frontend

Create the database:

```sql
CREATE DATABASE librarydb;
```

The current local properties use `localhost:3306`, database `librarydb`, user `root`, and password `root123`.

Build/test/run:

```bash
mvn clean package
mvn test
mvn spring-boot:run
```

Backend runs at `http://localhost:8081`.

For the static frontend:

```bash
cd frontend
python3 -m http.server 5500
```

Open `http://localhost:5500`.

> Docker Compose is preferred for the complete application because it supplies the MySQL service and uses the Compose hostname `mysql` for backend-to-database communication.

## Configuration

Current Spring Boot settings include:

```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
spring.datasource.username=root
spring.datasource.password=root123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

Docker Compose overrides the datasource URL for the backend with:

```text
jdbc:mysql://mysql:3306/librarydb
```

## REST API Reference

Base URL:

```text
http://localhost:8081/books
```

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/books` | List all books |
| `GET` | `/books/{id}` | Get one book |
| `POST` | `/books` | Add a book |
| `PUT` | `/books/{id}` | Update a book |
| `DELETE` | `/books/{id}` | Delete a book |
| `PUT` | `/books/issue/{id}?borrowerName={name}` | Issue a book |
| `PUT` | `/books/return/{id}` | Return a book |

### Get all books

```bash
curl http://localhost:8081/books
```

### Get one book

```bash
curl http://localhost:8081/books/1
```

A missing book raises `BookNotFoundException` and is handled as HTTP `404`.

### Add a book

```bash
curl -X POST http://localhost:8081/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Effective Java","author":"Joshua Bloch"}'
```

A missing status becomes `Available`. A duplicate title + author is rejected.

### Update a book

```bash
curl -X PUT http://localhost:8081/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code - Updated","author":"Robert C. Martin"}'
```

### Delete a book

```bash
curl -X DELETE http://localhost:8081/books/1
```

Successful deletion returns `Book deleted successfully.`

### Issue a book

```bash
curl -X PUT "http://localhost:8081/books/issue/1?borrowerName=Jyothi"
```

The service sets `status=Issued`, stores the borrower, sets `issueDate` to today, sets `dueDate` to today + 7 days, and clears `returnDate`. An already issued book is rejected.

### Return a book

```bash
curl -X PUT http://localhost:8081/books/return/1
```

The service sets `status=Available` and `returnDate` to today.

## Frontend Modules

- `index.html` — dashboard, statistics, quick actions, architecture/feature overview
- `books.html` — book table, search, pagination, view/edit/delete/issue/return
- `add-book.html` — create-book form
- `edit-book.html` — update-book form
- `about.html` — project and technology information
- `js/api.js` — API calls, rendering, search, pagination and issue/return logic
- `js/app.js` — page-load initialization
- `css/style.css` — application styling
- `nginx.conf` — Nginx static-site configuration

## Testing

`BookServiceTest` uses JUnit 5 and Mockito and covers retrieval, missing-book handling, creation, duplicate detection, update, deletion, issuing, duplicate issuance prevention, and returning.

```bash
mvn test
mvn clean package
```

## Docker Details

### Backend image

The root `Dockerfile` uses `eclipse-temurin:17-jre`, copies `target/LibraryManagementSystem-1.0-SNAPSHOT.jar` to `app.jar`, exposes `8081`, and starts it with `java -jar app.jar`.

```bash
mvn clean package
docker build -t librarymanagementsystem-library-app .
```

### Frontend image

`frontend/Dockerfile` uses `nginx:alpine`, copies the static site to `/usr/share/nginx/html`, and exposes port `80`.

```bash
docker build -t librarymanagementsystem-frontend ./frontend
```

### Compose topology

```text
mysql (3306)
   │ healthcheck
   ▼
library-app (8081)
   │
   ▼
frontend (80)
```

MySQL uses the persistent `mysql-data` volume. The backend depends on MySQL being healthy before startup.

## Jenkins CI/CD

The repository contains a `Jenkinsfile` with this workflow:

```text
Checkout
   ↓
Build (mvn clean compile)
   ↓
Test (mvn test)
   ↓
Package (mvn clean package -DskipTests)
   ↓
Build backend Docker image
   ↓
Build frontend Docker image
   ↓
Deploy with Docker Compose
   ↓
Health Check
```

The health check uses `curl` against `http://host.docker.internal:8081/books` and `http://host.docker.internal/` from the Jenkins environment and prints Compose status/container logs when a check fails.

The custom `jenkins/Dockerfile` starts from `jenkins/jenkins:lts-jdk17` and installs Docker CLI, Maven, curl, and Docker Compose v5.4.0. For the Dockerized Jenkins setup to control the host Docker daemon, the Jenkins container is run with `/var/run/docker.sock` mounted. The local setup used by this project runs Jenkins as a Docker container.

## Troubleshooting

### Maven says there is no POM

Run Maven from the checked-out repository workspace containing `pom.xml`:

```bash
pwd
ls
mvn clean compile
```

### Docker build cannot find the JAR

The backend Dockerfile expects:

```text
target/LibraryManagementSystem-1.0-SNAPSHOT.jar
```

Run:

```bash
mvn clean package
ls -l target/*.jar
docker build -t librarymanagementsystem-library-app .
```

### Jenkins cannot access Docker

Verify inside Jenkins:

```bash
docker exec jenkins-server docker ps
docker exec jenkins-server docker compose version
```

If Docker permission errors occur, verify that the Jenkins container has `/var/run/docker.sock` mounted and that the container user can access it. The current local setup uses `--user root` when running Jenkins.

### Jenkins says `not in a git directory`

This can occur when Jenkins' cached Pipeline SCM workspace/script state is corrupted. Clear the affected Jenkins workspace/cache and rerun the job after confirming the repository URL and Jenkinsfile configuration.

## Security and Production Notes

This is primarily a learning/demo project. Before production use:

- Move database credentials to environment variables/secrets management.
- Do not use the MySQL `root` account for the application.
- Restrict CORS. The controller currently allows `*`, while `WebConfig` also contains development origins.
- Add authentication and authorization, such as Spring Security and librarian/admin/user roles.
- Standardize API error responses.
- Prefer Flyway/Liquibase over `ddl-auto=update` for controlled schema migrations.
- Add stronger validation and business rules.
- Treat a mounted Docker socket in Jenkins as a high-privilege capability.
- Add integration tests and production monitoring.

## Development Workflow

```bash
git checkout -b feature/your-feature
mvn test
mvn clean package
docker compose up --build -d
git add .
git commit -m "feat: describe your change"
git push origin feature/your-feature
```

## Future Improvements

- Authentication and role-based authorization
- User/Borrower entities
- ISBN and categories
- Advanced filtering/sorting
- Server-side pagination
- Overdue detection and fines
- Borrowing history
- Swagger/OpenAPI documentation
- Integration tests and Testcontainers
- Flyway/Liquibase migrations
- Environment-based secrets
- Structured logging/monitoring
- CI quality gates and versioned Docker images

## Project Team

- **Salapu Rohit**
- **Salla Vamsi Ram**
- **Malla Jyothi Prakash**

B.Tech Computer Science & Engineering, Lovely Professional University.

## Additional Documentation

The repository also includes `intern PROJECT REPORT.docx` and project screenshots under `screenshots/`.

## License

No explicit open-source license is currently included in the repository. Add a `LICENSE` file if the project is intended for public reuse.

---

**Project flow:** Java → Spring Boot → REST API → JPA/Hibernate → MySQL → Docker → Docker Compose → Jenkins → Nginx/Bootstrap frontend.
