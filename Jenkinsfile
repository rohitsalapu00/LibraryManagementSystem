pipeline {

    agent any

    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

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

        stage('Health Check') {
            steps {
                sh '''
                    echo "Checking backend..."

                    backend_ready=false

                    for i in {1..10}; do
                        if curl -f http://localhost:8081/books; then
                            backend_ready=true
                            echo "Backend is healthy!"
                            break
                        fi

                        echo "Backend not ready yet. Waiting..."
                        sleep 3
                    done

                    if [ "$backend_ready" != "true" ]; then
                        echo "Backend health check failed!"
                        exit 1
                    fi

                    echo "Checking frontend..."

                    curl -f http://localhost/

                    echo "Frontend is healthy!"
                '''
            }
        }
    }

    post {

        success {
            echo 'CI/CD Pipeline Completed Successfully!'
        }

        failure {
            echo 'Pipeline Failed!'
        }
    }
}