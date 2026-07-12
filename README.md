# 🚀 Dockerized Jenkins Server for Library Management System

## 📌 Project Overview

This project demonstrates a complete Continuous Integration (CI) workflow by running Jenkins inside a Docker container to automate the build process of a Spring Boot Library Management System.

The project showcases modern DevOps practices including:

- Version Control using Git & GitHub
- Containerized Jenkins using Docker
- Automated Build Execution using Jenkins Pipeline
- Maven Build Automation
- Spring Boot Application Build
- Service Management using Docker
- Continuous Integration (CI) Workflow

The Library Management System is a Spring Boot application that manages books and demonstrates how Jenkins can automatically build Java applications whenever code changes are pushed to GitHub.

---

# 🎯 Project Objective

Traditional Java application deployment involves:

```
Developer writes code

        ↓

Manual compilation

        ↓

Manual Maven build

        ↓

Manual deployment

        ↓

Different environment issues
```

### Problems

- Time-consuming build process
- Manual deployment errors
- Dependency management issues
- Difficult collaboration
- No build automation

This project solves these issues using:

```
Developer

    ↓

GitHub Repository

    ↓

Dockerized Jenkins

    ↓

Git Clone

    ↓

Maven Build

    ↓

Spring Boot JAR

    ↓

Build Success
```

---

# 🏗 System Architecture

```
                 Developer

                      │

                      ▼

              GitHub Repository

                      │

                      ▼

          Dockerized Jenkins Server

              (Running in Docker)

                      │

                      ▼

             Jenkins Pipeline

                      │

                      ▼

             Maven Build Process

                      │

                      ▼

        Spring Boot Library Application

                      │

                      ▼

         Executable JAR Generated
```

---

# ⚙️ Technology Stack

## Backend

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL

## Build Tool

- Apache Maven

## DevOps Tools

- Git
- GitHub
- Docker
- Jenkins
- WSL2
- Ubuntu

---

# 📂 Project Structure

```
LibraryManagementSystem

│

├── src/

│   ├── main/

│   │   ├── java/

│   │   │   └── com/library/

│   │   │       ├── controller/

│   │   │       ├── entity/

│   │   │       ├── repository/

│   │   │       ├── service/

│   │   │       ├── config/

│   │   │       └── exception/

│   │   │

│   │   └── resources/

│   │

│   └── test/

│

├── Dockerfile

├── docker-compose.yml

├── Jenkinsfile

├── pom.xml

└── README.md
```

---

# ✨ Application Features

### Library Management

- Add New Books
- View All Books
- Update Book Details
- Delete Books

### REST API

- GET /books
- GET /books/{id}
- POST /books
- PUT /books/{id}
- DELETE /books/{id}

### Database

- MySQL Integration
- Spring Data JPA
- Hibernate ORM

---

# 🐳 Dockerized Jenkins Server

Docker is used to containerize Jenkins for consistent build automation.

## Why Docker?

Docker solves:

> "Jenkins works on one machine but not another."

Docker packages:

- Jenkins
- Java
- Maven
- Git
- Docker CLI

inside a single portable container.

---

# Docker Image

The custom Jenkins image includes:

- Jenkins LTS
- OpenJDK 21
- Apache Maven
- Git
- Docker CLI

Dockerfile

```dockerfile
FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y maven git docker.io

USER jenkins
```

---

# 🔄 Jenkins Pipeline Workflow

The Jenkins Pipeline automates the complete Java build process.

```
Developer

    ↓

git push

    ↓

GitHub Repository

    ↓

Jenkins Pipeline

    ↓

Checkout Source Code

    ↓

Compile Project

    ↓

Maven Build

    ↓

Generate Spring Boot JAR

    ↓

Build Successful
```

---

# Jenkins Pipeline Stages

## Stage 1 : Checkout

Clone latest source code from GitHub.

```
GitHub

↓

Jenkins Workspace
```

---

## Stage 2 : Build

Compile Java source files.

```
mvn clean compile
```

---

## Stage 3 : Package

Generate executable Spring Boot JAR.

```
mvn clean package
```

---

## Stage 4 : Post Actions

Display build status.

```
Build completed successfully!
```

---

# 🐳 Service Management

Manage Jenkins container using Docker.

Start Jenkins

```bash
docker start jenkins-server
```

Stop Jenkins

```bash
docker stop jenkins-server
```

Restart Jenkins

```bash
docker restart jenkins-server
```

View Running Containers

```bash
docker ps
```

View Logs

```bash
docker logs jenkins-server
```

---

# 🚀 Running the Project

## 1. Clone Repository

```bash
git clone https://github.com/rohitsalapu00/LibraryManagementSystem.git
```

Move into project

```bash
cd LibraryManagementSystem
```

---

## 2. Build Using Maven

```bash
mvn clean package
```

---

## 3. Run Spring Boot

```bash
mvn spring-boot:run
```

Application URL

```
http://localhost:8081
```

---

## 4. Run Dockerized Jenkins

```bash
docker run -d \
--name jenkins-server \
-p 9090:8080 \
-p 50000:50000 \
-v C:\Jenkins\jenkins_home:/var/jenkins_home \
-v //var/run/docker.sock:/var/run/docker.sock \
my-jenkins:latest
```

Jenkins Dashboard

```
http://localhost:9090
```

---

# 📌 CI Workflow Summary

```
Code

↓

Git Commit

↓

GitHub Push

↓

Jenkins Pipeline

↓

Maven Build

↓

Spring Boot JAR

↓

Build Success
```

---

# Challenges Faced

## WSL Configuration

### Problem

Docker Desktop required WSL2 configuration.

### Solution

Installed Ubuntu and configured WSL2 successfully.

---

## Jenkins Tool Configuration

### Problem

JDK and Maven were not recognized.

### Solution

Configured JDK17 and Maven paths inside Jenkins Global Tools.

---

## Docker Port Conflict

### Problem

Port 8080 was already occupied.

### Solution

Mapped Jenkins to port 9090.

---

## Maven Dependencies

### Problem

Initial build took a long time.

### Solution

Maven downloaded all dependencies during the first build. Subsequent builds became much faster.

---

# Future Enhancements

- Add Docker image build stage
- Push Docker images to Docker Hub
- Deploy application on AWS EC2
- Add Kubernetes deployment
- Integrate SonarQube for code quality
- Add automated testing stage
- Configure GitHub Webhooks
- Deploy using GitHub Actions

---

# Learning Outcomes

Through this project, I learned:

- Jenkins Installation
- Docker Containerization
- Docker Image Creation
- Docker Volume Management
- Jenkins Pipeline
- GitHub Integration
- Maven Build Automation
- Spring Boot Build Process
- Continuous Integration (CI)
- Service Management using Docker
- WSL2 Configuration
- DevOps Fundamentals

---

# 📊 Project Outcomes

✅ Containerized Jenkins Server

✅ Automated Build Execution

✅ GitHub Integration

✅ Maven Build Automation

✅ Spring Boot JAR Generation

✅ Docker-based Service Management

---

# Conclusion

This project successfully demonstrates how Jenkins can be containerized using Docker to automate the build process of a Spring Boot application.

By integrating GitHub, Maven, Docker, and Jenkins, the project showcases a practical Continuous Integration (CI) workflow that reduces manual effort, improves build consistency, and prepares the application for future Continuous Deployment (CD) enhancements.
