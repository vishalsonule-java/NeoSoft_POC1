pipeline {
    agent any
    
    environment {
       DISABLE_AUTH = 'true'
        JAVA_HOME "C:\Program Files\Java\jdk1.8.0_221"
   }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven-3.8.6"
      }

    stages {
        stage('Build') {
            steps {
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
}
