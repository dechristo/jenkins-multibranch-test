pipeline {
  agent any
  stages {
    stage('Unit Test') {
      when { tag "test-*" }
      steps {
        sh 'mvn test'
      }
    }

    stage('Integration Test') {
      when { tag "production-*" }
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
