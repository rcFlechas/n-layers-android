pipeline {

    agent {
        label 'Slave_Mac'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
     	disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK11_Mac'
    }

    stages {
        stage('Compile') {
            steps {
                echo "------------>Compile<------------"
                sh 'chmod +x ./gradlew'
                sh './gradlew build -x test'
            }
        }
        stage('Tests') {
            steps {
                echo "------------>Tests<------------"
                sh './gradlew clean'
                sh './gradlew jacocoTestReport'
            }
        }
        stage('Static Code Analysis') {
            steps {
                echo "------------>Static Code Analysis<------------"
                withSonarQubeEnv('Sonar') {
                    sh "${tool name: 'SonarScanner-Mac', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
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