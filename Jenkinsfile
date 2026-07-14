pipeline {
    agent any

    stages {

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
                sh '''
                    docker compose down --remove-orphans || true
                    docker compose up --build -d
                '''
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