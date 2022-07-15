pipeline {
    agent any

    stages {
        stage('Build') {
            steps {   
                withMaven(maven:'Maven'){
                sh 'mvn clean install'
                }
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                withMaven(maven:'Maven'){
                sh 'mvn clean compile'
                }
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
