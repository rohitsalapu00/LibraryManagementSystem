pipeline {

    agent any

    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/rohitsalapu00/LibraryManagementSystem.git'
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
                sh '''
                    echo "Stopping existing application containers..."

                    docker compose down --remove-orphans || true

                    echo "Starting application containers..."

                    docker compose up -d

                    echo "Application deployment completed!"
                '''
            }
        }

        stage('Health Check') {
            steps {
                sh '''
                    echo "Checking backend..."

                    backend_ready=false

                    for i in {1..10}
                    do
                        if curl -f http://localhost:8081/books
                        then
                            backend_ready=true
                            echo "Backend is healthy!"
                            break
                        else
                            echo "Backend not ready yet. Waiting..."
                            sleep 3
                        fi
                    done

                    if [ "$backend_ready" != "true" ]
                    then
                        echo "Backend health check failed!"
                        exit 1
                    fi

                    echo "Checking frontend..."

                    if curl -f http://localhost/
                    then
                        echo "Frontend is healthy!"
                    else
                        echo "Frontend health check failed!"
                        exit 1
                    fi

                    echo "All health checks passed!"
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