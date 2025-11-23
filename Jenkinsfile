pipeline {
    agent { label 'NODE1' }   // Hoặc agent any nếu bạn không dùng label

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/thanhtamkaito/spring-test.git',
                    branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew || true'
                sh './gradlew clean build'
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }
}
