Please use this in jenkins pipeline script to generate cucumber Options, in order to do that please ensure that you already insatlled cucumber report plugin  and configured JDK and MAVEN in Jenkins.

**Script**
==================================================================================================================================================================================    

    pipeline {
        agent any
        stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/dijaltom/cucumber_test2.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'mvn clean install -DskipTests=true'
            }
        }

        stage('Run Cucumber Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generate Cucumber Report') {
            steps {
                cucumber 'target/cucumber-reports/Cucumber.json'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            archiveArtifacts artifacts: 'target/cucumber-reports/*.json', allowEmptyArchive: true
        }

        success {
            echo 'Cucumber tests passed successfully!'
        }

        failure {
            echo 'Cucumber tests failed!'
        }
    }
}

 
**End**
