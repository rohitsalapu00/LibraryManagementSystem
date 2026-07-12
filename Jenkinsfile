pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
    }

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                sh 'docker build -t librarymanagementsystem-library-app .'
            }
        }

        stage('Build Frontend Docker Image') {
            steps {
                dir('frontend') {
                    sh 'docker build -t librarymanagementsystem-frontend .'
                }
            }
        }

        stage('Deploy Application') {
            steps {
                sh 'docker compose up --build -d'
            }
        }
    }

    post {
        success {
            echo 'Application Built and Deployed Successfully!'
        }

        failure {
            echo 'Pipeline Failed!'
        }
    }
}