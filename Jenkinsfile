pipeline {
  agent any
  stages {
    stage('Unit Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Integration Test') {
      steps {
        sh 'mvn test-integration'
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }

  }
}