pipeline {

    agent {
        label 'Slave_Mac'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
     	disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK8_Mac'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "------------>Checkout<------------"
                checkout scm
            }
        }
        stage('Compile') {
            steps {
                echo "------------>Compile<------------"
                sh 'chmod +x ./gradlew'
                sh './gradlew build -x test'
            }
        }
        stage('Unit Tests') {
            steps {
                echo "------------>Unit Tests<------------"
                sh './gradlew clean'
                sh './gradlew test'
                sh './gradlew jacocoTestReport'
            }
        }
        stage('Static Code Analysis') {
            steps {
                echo "------------>Static Code Analysis<------------"
                withSonarQubeEnv('Sonar') {
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -D project.settings=sonar-project.properties"
                }
            }
        }
    }

    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
            junit 'build/test-results/test/*.xml'
        }
        failure {
            echo 'This will run only if failed'
            mail (to: 'renzon.caceres@ceiba.com.co', subject: "Failed Pipeline:${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}")
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}