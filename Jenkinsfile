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
        stage('Compile') {
            steps {
                echo "------------>Compile<------------"
            }
        }
        stage('Unit Tests') {
            steps {
                echo "------------>Unit Tests<------------"
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
        }
        failure {
            echo 'This will run only if failed'
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