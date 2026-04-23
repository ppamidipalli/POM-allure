pipeline {
    agent any

    tools {
        jdk 'jdk11'
        gradle 'gradle'
    }

    parameters {
        choice(name: 'ENV', choices: ['qa', 'uat', 'prod'], description: 'Execution environment')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Browser to run')
        string(name: 'TAGS', defaultValue: '@smoke', description: 'Cucumber tags to execute')
        string(name: 'THREADS', defaultValue: '2', description: 'Parallel thread count')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run in headless mode')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Test') {
            steps {
                sh "gradle clean test -Denv=${params.ENV} -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} -Dthreads=${params.THREADS} -Dcucumber.filter.tags='${params.TAGS}'"
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh "gradle allureReport"
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/reports/**/*, build/screenshots/**/*, build/allure-results/**/*', allowEmptyArchive: true
            junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
        }
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check screenshots and reports for diagnostics.'
        }
    }
}
