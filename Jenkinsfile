pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven-3.8.6"
    }

    stages {
        stage('Build') {
            steps {
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
}
