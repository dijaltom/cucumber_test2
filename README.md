Please use this in jenkins pipeline script to generate cucumber Options, in order to do that please ensure that you already insatlled cucumber report plugin  and configured JDK and MAVEN in Jenkins.

**Script**
==================================================================================================================================================================================
pipeline {
    agent any

   environment {
        // Define paths for the reports
       CUCUMBER_JSON_REPORT = 'target/cucumber-reports/Cucumber.json'
     }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                git branch:'master', url:'https://github.com/dijaltom/cucumber_test2.git'
                }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    // Install required dependencies (Maven, Java, etc.)
                    bat 'mvn clean install -DskipTests=true'
                }
            }
        }

        stage('Run Cucumber Tests') {
            steps {
                script {
                    // Run the Cucumber tests and generate a JSON report
                    bat 'mvn test -Dcucumber.options="--plugin json:target/cucumber-reports/Cucumber.json"'
                }
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
            // Clean up or notify
            echo 'Cleaning up...'
        }

        success {
            // Notify success, if needed
            echo 'Cucumber tests passed successfully!'
        }

        failure {
            // Notify failure, if needed
            echo 'Cucumber tests failed!'
        }
    }
}
===================================================================================================================================================================
**End**
