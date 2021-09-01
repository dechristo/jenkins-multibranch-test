pipeline {
  agent any
  stages {
 
    stage("Prepare") {
       steps {
          sh 'git fetch --all --tags'
       }
    }

    stage('Unit Test') {
      when { tag "test-*" }
      steps {
        sh 'echo "[test] Running Unit tests..."'
      }
    }

    stage('Integration Test') {
      when { tag "production-*" }
      steps {
        sh 'echo "[prod] running integration test"'
      }
    }

    stage('Build') {
      steps {
        sh 'echo "[all] clean building..."'
      }
    }

  }
}
