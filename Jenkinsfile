pipeline {
    agent any
    
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'Select the version')
        booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Run tests?')
    }
    
    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
            }
        }
        stage('Test') {
            when {
                expression { params.RUN_TESTS }
            }
            steps {
                echo 'Running tests...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                 echo "Version: ${params.VERSION}"
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline succeeded. Congratulations!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
