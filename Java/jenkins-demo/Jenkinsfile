pipeline {
    agent any

    stages {
        stage('Build, Test, and Generate Coverage') {
            steps {
                bat "mvn clean verify"
                bat "mvn jacoco:prepare-agent"
            }
        }
        stage('SonarQube Analysis') {
            steps {
                bat "mvn sonar:sonar -Dsonar.projectKey=jenkins-pipeline -Dsonar.projectName='jenkins-pipeline' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_7e331e657bb7581c1099562852b47560d9c0e50a -Dsonar.java.coveragePlugin=jacoco"
            }
        }
    }
}