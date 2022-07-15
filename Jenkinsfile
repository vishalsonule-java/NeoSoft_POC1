pipeline {
    agent any

    stages {
        stage('Build') {
            steps {   
                withMaven{
                sh 'mvn clean install'
                }
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                withMaven{
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
