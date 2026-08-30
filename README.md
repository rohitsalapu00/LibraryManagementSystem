# 🚀 Library Management System

A full-stack **Library Management System** built with **Spring Boot, MySQL, HTML, CSS, JavaScript, Bootstrap, Docker, and Jenkins**.

The project demonstrates a complete **CI/CD workflow** where Jenkins automatically builds, tests, packages, containerizes, and deploys the application using Docker Compose.

---

## 📌 Project Overview

The Library Management System allows users to:

- 📚 View books
- ➕ Add books
- ✏️ Update books
- 🗑️ Delete books
- 🔍 Search books
- 📖 Issue books
- 🔄 Return books
- 🚫 Prevent duplicate books

The application consists of three main services:

````text
Frontend
   │
   ▼
Spring Boot Backend
   │
   ▼
MySQL Database
🏗️ Project Architecture
Developer
    │
    ▼
  GitHub
    │
    ▼
  Jenkins
    │
    ├── Build
    ├── Test
    ├── Package
    ├── Build Docker Images
    └── Deploy
            │
            ▼
      Docker Compose
        │     │     │
        ▼     ▼     ▼
     MySQL Backend Frontend
⚙️ Technologies Used
Technology	Purpose
Java 17	Programming Language
Spring Boot	Backend Framework
Spring Data JPA	Database Access
MySQL 8.4	Database
Maven	Build Tool
HTML	Frontend Structure
CSS	Frontend Styling
JavaScript	Frontend Logic
Bootstrap 5	Responsive UI
Docker	Containerization
Docker Compose	Multi-container Deployment
Jenkins	CI/CD Automation
Git	Version Control
GitHub	Source Code Repository
Nginx	Frontend Web Server
✨ Features
📚 Library Features
Add a new book
View all books
Update book details
Delete books
Search books
Issue books
Return books
Track borrower information
Track issue, due, and return dates
Prevent duplicate books
🛡️ Validation

The backend validates:

Empty book title
Empty author name
Duplicate title and author
Issuing an already issued book
Invalid book IDs
🧪 Testing

The project contains unit tests for BookService.

Current test result:

Tests run: 11
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
🚫 Duplicate Book Prevention

Duplicate books are prevented using the repository method:

existsByTitleAndAuthor(String title, String author)

Before adding a book, the service checks whether a book with the same title and author already exists.

If it exists, the application returns an error instead of creating another copy.

📂 Project Structure
LibraryManagementSystem/
│
├── frontend/
│   ├── css/
│   ├── js/
│   ├── pages/
│   └── Dockerfile
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/library/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── entity/
│   │   │       ├── exception/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   │
│   │   └── resources/
│   │
│   └── test/
│       └── java/
│           └── com/library/
│               └── service/
│                   └── BookServiceTest.java
│
├── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
├── pom.xml
└── README.md
🤖 Jenkins CI/CD Pipeline

The project uses a Declarative Jenkins Pipeline.

The pipeline automatically performs the following stages:

Checkout
   ↓
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
🔄 Jenkins Pipeline Stages
Stage	Description
Checkout SCM	Gets the latest source code from GitHub
Build	Compiles the Spring Boot application
Test	Runs unit tests using Maven
Package	Creates the executable Spring Boot JAR
Build Backend Docker Image	Creates the backend Docker image
Build Frontend Docker Image	Creates the frontend Docker image
Deploy Application	Starts the application using Docker Compose
Health Check	Verifies backend and frontend availability
❤️ Deployment Health Check

After deployment, Jenkins checks whether the application is working correctly.

Backend Check
http://localhost:8081/books

Jenkins waits for the Spring Boot backend to become available.

Frontend Check
http://localhost/

Jenkins verifies that Nginx successfully serves the frontend.

Example successful result:

Backend is healthy!
Frontend is healthy!
CI/CD Pipeline Completed Successfully!

Finished: SUCCESS
🐳 Docker

The application uses multiple Docker containers.

┌─────────────────────┐
│      Frontend       │
│       Nginx         │
│      Port 80        │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│      Backend        │
│    Spring Boot      │
│     Port 8081       │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│       MySQL         │
│     Port 3306       │
└─────────────────────┘
🐳 Docker Containers

After deployment, the following application containers are running:

Container	Purpose
librarymanagementpipeline-frontend-1	Frontend
librarymanagementpipeline-library-app-1	Spring Boot Backend
librarymanagementpipeline-mysql-1	MySQL Database

Jenkins runs separately.

🐳 Docker Commands
Start Application
docker compose up --build -d
Stop Application
docker compose down
Restart Application
docker compose restart
View Running Containers
docker ps
View Application Logs
docker compose logs
View Backend Logs
docker compose logs library-app
🤖 Jenkins

Jenkins is used to automate the CI/CD process.

The Jenkins server performs:

GitHub
   ↓
Checkout
   ↓
Maven Build
   ↓
Unit Tests
   ↓
Package
   ↓
Docker Build
   ↓
Docker Compose
   ↓
Deployment
💻 Running the Project Locally
1. Clone the Repository
git clone https://github.com/rohitsalapu00/LibraryManagementSystem.git
2. Enter the Project
cd LibraryManagementSystem
3. Build the Backend
mvn clean package
4. Run Tests
mvn test
5. Start the Application with Docker
docker compose up --build -d
🌐 Application URLs
Frontend
http://localhost/
Backend API
http://localhost:8081/books
Jenkins
http://localhost:9090
🔌 REST API

The backend provides REST APIs for managing books.

Get All Books
GET /books
Get Book by ID
GET /books/{id}
Add Book
POST /books
Update Book
PUT /books/{id}
Delete Book
DELETE /books/{id}
Issue Book
PUT /books/{id}/issue
Return Book
PUT /books/{id}/return
🗄️ Database

The application uses:

Database: MySQL
Version: 8.4
Database Name: librarydb

The main table is:

books

The book information includes:

id
title
author
status
borrowerName
issueDate
dueDate
returnDate
📊 Current Database Status

The database was verified after deployment.

Current number of books:

4

Duplicate title-author combinations:

0

Example verification query:

SELECT title, author, COUNT(*) AS copies
FROM books
GROUP BY title, author
HAVING COUNT(*) > 1;

Result:

Empty set
🧪 Build and Test Results

The Jenkins pipeline successfully performs:

✅ GitHub Checkout
✅ Maven Compilation
✅ Unit Testing
✅ Spring Boot Packaging
✅ Backend Docker Image Creation
✅ Frontend Docker Image Creation
✅ Docker Compose Deployment
✅ Backend Health Check
✅ Frontend Health Check

Latest pipeline result:

Finished: SUCCESS

Unit tests:

Tests run: 11
Failures: 0
Errors: 0
Skipped: 0
📸 Screenshots

Screenshots can be stored in the screenshots directory.

Example:

screenshots/
├── Home.png
├── Books.png
├── AddBooks.png
├── docker-container-running.png
├── docker-ps-output.png
├── jenkins-dashboard-success.png
└── pipeline-console-success.png
⚠️ Challenges Faced
Maven PATH

Jenkins initially could not find Maven:

mvn: command not found

The Jenkins environment PATH was configured to include the required tools.

Docker PATH

Jenkins also initially could not access Docker.

The Jenkins environment was updated so that Docker could be executed by the pipeline.

Duplicate Books

The application was initially creating duplicate books during startup.

This was fixed by checking whether the title and author already exist before inserting the book.

Docker Deployment

The pipeline was configured to stop old containers and deploy the latest application using Docker Compose.

Application Startup Time

The backend sometimes required additional time to start.

A health-check stage was added to wait for the backend before marking deployment as successful.

📚 Learning Outcomes

This project provided practical experience with:

Java
Spring Boot
REST APIs
Spring Data JPA
MySQL
Maven
Unit Testing
Mockito
HTML
CSS
JavaScript
Bootstrap
Docker
Docker Compose
Jenkins
CI/CD
Git
GitHub
Linux command-line tools
🎯 Project Outcome

The project demonstrates a complete development and deployment workflow:

Code
 ↓
Git
 ↓
GitHub
 ↓
Jenkins
 ↓
Maven
 ↓
Unit Tests
 ↓
JAR
 ↓
Docker Images
 ↓
Docker Compose
 ↓
MySQL + Backend + Frontend
 ↓
Health Checks
 ↓
Running Application
🔮 Future Enhancements

Possible future improvements include:

SonarQube code quality analysis
GitHub Webhooks
Docker Hub image publishing
Kubernetes deployment
AWS deployment
Prometheus monitoring
Grafana dashboards
More automated tests
Authentication and authorization
Role-based access control
👨‍💻 Developed By
Member	Contribution
Salapu Rohit	Spring Boot Development, GitHub Repository Management
Salla Vamsi Ram	Library Management System Development & Testing
Malla Jyothi Prakash	Dockerized Jenkins, Jenkins Pipeline, CI/CD Integration and Documentation

All members are pursuing:

B.Tech Computer Science & Engineering
Lovely Professional University
📂 Repository

GitHub Repository:

https://github.com/rohitsalapu00/LibraryManagementSystem

📊 Project Summary
Category	Details
Project Type	Full Stack DevOps Project
Backend	Spring Boot
Frontend	HTML, CSS, JavaScript
Database	MySQL 8.4
Testing	JUnit / Mockito
Build Tool	Maven
CI/CD	Jenkins
Containerization	Docker
Deployment	Docker Compose
Web Server	Nginx
Version Control	Git & GitHub
Java Version	17
⭐ Conclusion

This project demonstrates how a Spring Boot application can be integrated with GitHub, Jenkins, Maven, Docker, Docker Compose, and MySQL to create an automated CI/CD workflow.

The pipeline successfully builds, tests, packages, containerizes, deploys, and performs health checks on the application.

<div align="center">
⭐ Thank You for Visiting Our Repository ⭐

Library Management System Team

</div> ```
````
