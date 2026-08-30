# 📚 Library Management System

A full-stack Library Management System built with **Java 17**, **Spring Boot**, **Spring Data JPA**, **MySQL**, and a responsive **HTML/CSS/JavaScript + Bootstrap 5** frontend.

The application provides book management through a REST API and web UI, including CRUD operations, book issuing/returning, search, pagination, validation, Dockerized deployment, and a Jenkins CI/CD pipeline.

## ✨ Highlights

- 📖 Manage books with full CRUD operations
- 🔎 Search books by title or author
- 📄 Paginated book listing
- 📤 Issue books to borrowers with a 7-day due date
- 📥 Return issued books
- 🛡️ Validation and centralized exception handling
- 🗄️ MySQL persistence through Spring Data JPA
- 🐳 Docker and Docker Compose support
- 🔄 Jenkins-based build, test, packaging, image creation, deployment, and health checks
- 📱 Responsive Bootstrap 5 frontend

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.5.4 |
| REST API | Spring Web |
| Persistence | Spring Data JPA / Hibernate |
| Database | MySQL 8.4 |
| Validation | Jakarta Bean Validation |
| Frontend | HTML5, CSS3, JavaScript |
| UI Framework | Bootstrap 5.3.3 |
| Icons | Font Awesome 6.5.2 |
| Build Tool | Maven |
| Testing | JUnit 5, Mockito |
| Containerization | Docker |
| Orchestration | Docker Compose |
| CI/CD | Jenkins |
| Web Server | Nginx Alpine |
| Version Control | Git / GitHub |

## 🏷️ Badges

**Technology**

![Java 17](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.4-blue?logo=mysql)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?logo=jenkins)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.3-7952B3?logo=bootstrap)

**Project status**

![Tests](https://img.shields.io/badge/tests-JUnit%20%2B%20Mockito-informational)
![License](https://img.shields.io/badge/license-not%20specified-lightgrey)

> **License:** No `LICENSE` file or explicit license declaration is present in the analyzed repository. Add an appropriate license before distributing the project as open source.

---

## 📌 Core Features

### Book Management

- Add a new book with title and author
- View all books
- View a single book by ID
- Update an existing book
- Delete a book
- Prevent duplicate books with the same title and author

### Borrowing Workflow

- Issue an available book to a borrower
- Automatically set the issue date
- Automatically calculate a due date 7 days after issue
- Prevent issuing a book that is already issued
- Return an issued book
- Record the return date
- Display borrower and circulation details

### Frontend Experience

- Dashboard with total book and author statistics
- Backend availability indicator
- Recently added books
- Search by title or author
- Five books per page
- View-book modal
- Add-book form
- Edit-book form
- Delete confirmation
- Issue/return actions
- Toast notifications
- Responsive Bootstrap layout

---

## 🏗️ Architecture

The project follows a conventional layered Spring Boot architecture:

```text
                        ┌──────────────────────┐
                        │   Browser / Frontend │
                        │ HTML + JS + Bootstrap│
                        └──────────┬───────────┘
                                   │ HTTP / JSON
                                   ▼
                        ┌──────────────────────┐
                        │   BookController     │
                        │    REST API Layer    │
                        └──────────┬───────────┘
                                   │
                                   ▼
                        ┌──────────────────────┐
                        │     BookService      │
                        │   Business Logic     │
                        └──────────┬───────────┘
                                   │
                                   ▼
                        ┌──────────────────────┐
                        │   BookRepository     │
                        │   Spring Data JPA    │
                        └──────────┬───────────┘
                                   │
                                   ▼
                        ┌──────────────────────┐
                        │       MySQL          │
                        │      librarydb       │
                        └──────────────────────┘
```

### DevOps Flow

```text
Developer
   │
   ▼
Git / GitHub
   │
   ▼
Jenkins
   │
   ├── Build
   ├── Test
   ├── Package
   ├── Build Docker Images
   ├── Deploy with Docker Compose
   └── Health Check
   │
   ▼
Running Application
```

---

## 📂 Project Structure

```text
LibraryManagementSystem/
│
├── frontend/
│   ├── css/
│   │   └── style.css
│   │
│   ├── js/
│   │   ├── api.js
│   │   └── app.js
│   │
│   ├── screenshots/
│   │   ├── About.png
│   │   ├── AddBooks.png
│   │   ├── Books.png
│   │   ├── Home.png
│   │   ├── docker-container-running.png
│   │   ├── docker-ps-output.png
│   │   ├── jenkins-dashboard-success.png
│   │   └── pipeline-console-success.png
│   │
│   ├── about.html
│   ├── add-book.html
│   ├── books.html
│   ├── edit-book.html
│   ├── index.html
│   ├── Dockerfile
│   ├── nginx.conf
│   └── .dockerignore
│
├── src/
│   ├── main/
│   │   ├── java/com/library/
│   │   │   ├── config/
│   │   │   │   ├── DataLoader.java
│   │   │   │   └── WebConfig.java
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   └── BookController.java
│   │   │   │
│   │   │   ├── entity/
│   │   │   │   └── Book.java
│   │   │   │
│   │   │   ├── exception/
│   │   │   │   ├── BookNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   └── BookRepository.java
│   │   │   │
│   │   │   ├── service/
│   │   │   │   └── BookService.java
│   │   │   │
│   │   │   └── LibraryManagementSystemApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/com/library/service/
│           └── BookServiceTest.java
│
├── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
├── pom.xml
├── .gitignore
├── intern PROJECT REPORT.docx
└── README.md
```

### Backend Package Responsibilities

| Package | Responsibility |
|---|---|
| `controller` | Exposes REST endpoints |
| `service` | Contains book-management business logic |
| `repository` | Provides database access through Spring Data JPA |
| `entity` | Defines the `Book` database entity |
| `exception` | Defines and handles application exceptions |
| `config` | Seeds initial data and configures web behavior |

---

## 📋 Book Data Model

The `Book` entity is stored in the `books` table.

| Field | Type | Description |
|---|---|---|
| `id` | `int` | Auto-generated primary key |
| `title` | `String` | Book title; required |
| `author` | `String` | Book author; required |
| `status` | `String` | `Available` or `Issued` |
| `borrowerName` | `String` | Current borrower |
| `issueDate` | `LocalDate` | Date the book was issued |
| `dueDate` | `LocalDate` | Seven days after issue |
| `returnDate` | `LocalDate` | Date the book was returned |

New books default to `Available`.

---

# 🚀 Getting Started

## Prerequisites

### Option A — Run locally

Install:

- Java 17 or later
- Maven
- MySQL 8.x
- Git
- A modern web browser

### Option B — Run with Docker Compose

Install:

- Docker
- Docker Compose

This is the simplest way to start MySQL, the Spring Boot backend, and the Nginx frontend together.

---

## ⚙️ Configuration

The local Spring Boot configuration uses:

```properties
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
spring.datasource.username=root
spring.datasource.password=root123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

The repository also contains a Docker Compose configuration that creates the `librarydb` database and exposes the backend on port `8081` and frontend on port `80`.

> **Production note:** Do not keep database passwords in source control. Use environment variables or a secrets manager for production deployments.

---

# 💻 Local Installation

## 1. Clone the repository

```bash
git clone https://github.com/rohitsalapu00/LibraryManagementSystem
cd LibraryManagementSystem
```

## 2. Create the MySQL database

Start MySQL and create the database:

```sql
CREATE DATABASE librarydb;
```

Make sure the MySQL credentials match your local configuration.

## 3. Build the backend

```bash
mvn clean package
```

To run the tests separately:

```bash
mvn test
```

## 4. Start the Spring Boot backend

```bash
mvn spring-boot:run
```

The backend runs on:

```text
http://localhost:8081
```

The REST API base URL is:

```text
http://localhost:8081/books
```

## 5. Start the frontend

The frontend is a static website.

For local development, serve the `frontend/` directory with a local HTTP server. For example, if Python is installed:

```bash
cd frontend
python3 -m http.server 5500
```

Then open:

```text
http://localhost:5500
```

The frontend JavaScript is configured to call:

```text
http://localhost:8081/books
```

---

# 🐳 Docker Compose Setup

Docker Compose starts three services:

```text
mysql
   │
   ▼
library-app
   │
   ▼
frontend
```

The Compose configuration uses:

- MySQL 8.4
- Spring Boot backend on port `8081`
- Nginx frontend on port `80`
- A persistent `mysql-data` volume
- A MySQL health check before starting the backend

## 1. Build and start everything

From the project root:

```bash
docker compose up --build -d
```

## 2. Check running containers

```bash
docker compose ps
```

You should see services corresponding to:

```text
mysql
library-app
frontend
```

## 3. Open the application

Frontend:

```text
http://localhost/
```

Backend API:

```text
http://localhost:8081/books
```

## 4. View logs

Backend:

```bash
docker compose logs -f library-app
```

Frontend:

```bash
docker compose logs -f frontend
```

MySQL:

```bash
docker compose logs -f mysql
```

## 5. Stop the application

```bash
docker compose down
```

To also remove the persistent database volume:

```bash
docker compose down -v
```

> Removing the volume deletes the MySQL data stored by this Compose deployment.

---

# 🔌 REST API Reference

Base URL:

```text
http://localhost:8081/books
```

## Get all books

```http
GET /books
```

Example:

```bash
curl http://localhost:8081/books
```

Example response:

```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "status": "Available",
    "borrowerName": null,
    "issueDate": null,
    "dueDate": null,
    "returnDate": null
  }
]
```

## Get a book by ID

```http
GET /books/{id}
```

Example:

```bash
curl http://localhost:8081/books/1
```

If the book does not exist, the backend raises `BookNotFoundException` and returns HTTP `404`.

---

## Add a book

```http
POST /books
Content-Type: application/json
```

Example:

```bash
curl -X POST http://localhost:8081/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Effective Java",
    "author": "Joshua Bloch"
  }'
```

The service assigns `Available` when no status is supplied.

Duplicate title + author combinations are rejected.

---

## Update a book

```http
PUT /books/{id}
Content-Type: application/json
```

Example:

```bash
curl -X PUT http://localhost:8081/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Clean Code - Updated",
    "author": "Robert C. Martin"
  }'
```

---

## Delete a book

```http
DELETE /books/{id}
```

Example:

```bash
curl -X DELETE http://localhost:8081/books/1
```

Successful deletion returns:

```text
Book deleted successfully.
```

---

## Issue a book

```http
PUT /books/issue/{id}?borrowerName={name}
```

Example:

```bash
curl -X PUT \
  "http://localhost:8081/books/1/issue?borrowerName=Jyothi"
```

When a book is issued:

- `status` becomes `Issued`
- `borrowerName` is stored
- `issueDate` becomes the current date
- `dueDate` becomes 7 days after the issue date
- `returnDate` is cleared

An already issued book cannot be issued again.

---

## Return a book

```http
PUT /books/return/{id}
```

Example:

```bash
curl -X PUT http://localhost:8081/books/return/1
```

When returned:

- `status` becomes `Available`
- `returnDate` becomes the current date

---

# 🖥️ Using the Web Interface

## Dashboard

Open:

```text
http://localhost/
```

The home page provides:

- Total books
- Total authors
- Backend status
- MySQL status indicator
- Docker status indicator
- Recently added books
- Quick actions
- Project architecture and feature overview

## Add a book

1. Open **Add Book**.
2. Enter the title.
3. Enter the author.
4. Click **Save Book**.
5. The frontend sends a `POST /books` request.
6. After success, it redirects to the Books page.

Empty title or author values are rejected by the frontend and backend validation.

## Search books

1. Open **Books**.
2. Enter a keyword in the search box.
3. The frontend filters books by title or author.
4. Matching results are displayed immediately.

## View book details

Click **View** beside a book.

The modal displays:

- ID
- Title
- Author
- Status
- Borrower
- Issue date
- Due date
- Return date

## Edit a book

1. Click **Edit**.
2. The application opens `edit-book.html?id=<id>`.
3. Existing book details are loaded.
4. Modify the title or author.
5. Click **Update Book**.

## Delete a book

1. Click **Delete**.
2. Confirm the browser confirmation dialog.
3. The frontend sends a `DELETE` request.
4. The book list refreshes after successful deletion.

## Issue a book

1. Find an `Available` book.
2. Click **Issue**.
3. Enter the borrower name.
4. The frontend sends the issue request.
5. The book changes to `Issued`.

## Return a book

For an `Issued` book:

1. Click **Return**.
2. The frontend sends the return request.
3. The status changes back to `Available`.

---

# 🧪 Testing

The project contains unit tests for `BookService` using **JUnit 5** and **Mockito**.

The tests cover:

- Getting all books
- Getting a book by ID
- Handling a missing book
- Adding a book
- Rejecting duplicate books
- Updating a book
- Deleting an existing book
- Handling deletion of a missing book
- Issuing a book
- Preventing duplicate issuance
- Returning a book

Run:

```bash
mvn test
```

Run a full build:

```bash
mvn clean package
```

---

# 🔄 Jenkins CI/CD Pipeline

The repository includes a `Jenkinsfile` defining the CI/CD workflow.

The pipeline performs:

```text
Build
  ↓
Test
  ↓
Package
  ↓
Build Backend Docker Image
  ↓
Build Frontend Docker Image
  ↓
Deploy Application
  ↓
Health Check
```

### Pipeline stages

| Stage | Command / Purpose |
|---|---|
| Build | `mvn clean compile` |
| Test | `mvn test` |
| Package | `mvn clean package -DskipTests` |
| Backend image | Builds the Spring Boot Docker image |
| Frontend image | Builds the Nginx Docker image |
| Deploy | `docker compose down` + `docker compose up --build -d` |
| Health Check | Checks backend `/books` and frontend `/` |

Jenkins also publishes JUnit results from:

```text
target/surefire-reports/*.xml
```

The health check waits for the backend to become available before marking the deployment successful.

---

# 🐋 Docker Images

## Backend

The root `Dockerfile` uses a Java 17 runtime image:

```dockerfile
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/LibraryManagementSystem-1.0-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build manually:

```bash
mvn clean package
docker build -t librarymanagementsystem-library-app .
```

Run manually:

```bash
docker run --rm -p 8081:8081 \
  librarymanagementsystem-library-app
```

> The backend container expects the configured database to be reachable. Docker Compose is recommended because it provides the MySQL service and the correct internal database hostname.

## Frontend

The frontend is served by Nginx:

```dockerfile
FROM nginx:alpine

COPY . /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
```

Build manually:

```bash
docker build -t librarymanagementsystem-frontend ./frontend
```

Run manually:

```bash
docker run --rm -p 80:80 \
  librarymanagementsystem-frontend
```

---

# 🔐 Security & Production Notes

The repository is suitable as a learning/demo project, but several items should be addressed before a public production deployment.

### 1. Move database credentials out of source control

The current configuration contains:

```properties
spring.datasource.username=root
spring.datasource.password=root123
```

Use environment variables or a secrets manager instead.

### 2. Use a dedicated database user

Avoid using the MySQL `root` account for the application.

Create a restricted application user with only the permissions it needs.

### 3. Restrict CORS

The controller currently allows all origins:

```java
@CrossOrigin(origins = "*")
```

`WebConfig` also contains development origins for:

```text
http://127.0.0.1:5500
http://localhost:5500
```

For production, allow only the actual frontend origin.

### 4. Add authentication and authorization

The current API does not contain an authentication layer.

A production library system should consider:

- Spring Security
- User accounts
- Roles such as librarian/admin/user
- Protected write operations
- Secure password storage

### 5. Improve API error consistency

The current exception handler covers `BookNotFoundException` and `IllegalArgumentException`. A production API could standardize all errors into a JSON structure such as:

```json
{
  "timestamp": "2026-08-30T20:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Book with the same title and author already exists",
  "path": "/books"
}
```

### 6. Use database migrations

Instead of relying on:

```properties
spring.jpa.hibernate.ddl-auto=update
```

consider Flyway or Liquibase for controlled schema migrations.

### 7. Add stronger validation

Consider validating:

- Borrower name
- Maximum field lengths
- Allowed status values
- Update rules for issued books
- Duplicate handling during updates

---

# 🗺️ Roadmap

Potential improvements for future versions:

- [ ] Add authentication and role-based authorization
- [ ] Add separate `User` and `Borrower` entities
- [ ] Add book categories and ISBN
- [ ] Add author management
- [ ] Add advanced filtering and sorting
- [ ] Add server-side pagination
- [ ] Add overdue-book detection
- [ ] Add fine calculation
- [ ] Add borrowing history
- [ ] Add dashboard charts
- [ ] Add REST API documentation with OpenAPI/Swagger
- [ ] Add integration tests
- [ ] Add Testcontainers for database testing
- [ ] Add Flyway/Liquibase migrations
- [ ] Move secrets to environment variables
- [ ] Add structured logging and monitoring
- [ ] Add CI quality gates
- [ ] Publish versioned Docker images
- [ ] Add production deployment configuration

---

# 🤝 Contributing

Contributions are welcome.

## Development workflow

1. Fork the repository.
2. Create a feature branch:

```bash
git checkout -b feature/your-feature
```

3. Make your changes.
4. Run the tests:

```bash
mvn test
```

5. Build the application:

```bash
mvn clean package
```

6. Test the Docker Compose deployment if your changes affect deployment:

```bash
docker compose up --build -d
```

7. Commit your changes:

```bash
git add .
git commit -m "feat: describe your change"
```

8. Push your branch:

```bash
git push origin feature/your-feature
```

9. Open a Pull Request.

## Contribution guidelines

- Keep changes focused and easy to review.
- Follow the existing package structure.
- Add or update tests for backend behavior changes.
- Do not commit passwords, API keys, tokens, or other secrets.
- Update this README when setup or behavior changes.
- Verify Docker Compose after changes to deployment configuration.

---

# 🧹 Useful Commands

### Maven

```bash
mvn clean
mvn compile
mvn test
mvn clean package
mvn spring-boot:run
```

### Docker Compose

```bash
docker compose up --build -d
docker compose ps
docker compose logs -f
docker compose down
docker compose down -v
```

### Git

```bash
git status
git add .
git commit -m "your message"
git push
```

---

# 📊 Application Flow

```text
User
 │
 ▼
Bootstrap Frontend
 │
 │ Fetch /books
 ▼
Spring Boot REST API
 │
 ▼
BookController
 │
 ▼
BookService
 │
 ▼
BookRepository
 │
 ▼
MySQL
 │
 ▼
JSON Response
 │
 ▼
Frontend UI
```

For an issue operation:

```text
User clicks "Issue"
        │
        ▼
Enter borrower name
        │
        ▼
PUT /books/issue/{id}?borrowerName=...
        │
        ▼
Check book exists
        │
        ▼
Check status != Issued
        │
        ▼
status = Issued
issueDate = today
dueDate = today + 7 days
returnDate = null
        │
        ▼
Save to MySQL
        │
        ▼
Refresh book list
```

---

# 👥 Project Team

The frontend project information identifies the following developers:

- **Salapu Rohit**
- **Salla Vamsi Ram**
- **Malla Jyothi Prakash**

**B.Tech Computer Science & Engineering**  
**Lovely Professional University**

---

## 📄 Project Documentation

The repository also contains:

```text
intern PROJECT REPORT.docx
```

Use the project report alongside this README for academic/project documentation.

---

## 📜 License

No explicit open-source license is currently included in the repository.

If this project is intended for public reuse, add a `LICENSE` file and replace the badge above with the selected license.

---

## ⭐ Acknowledgement

Built as a full-stack and DevOps-oriented Library Management System demonstrating:

**Java → Spring Boot → REST API → JPA → MySQL → Docker → Jenkins → Bootstrap**

