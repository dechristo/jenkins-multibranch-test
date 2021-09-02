pipeline {
  agent any
  
  parameters {
        gitParameter name: 'TAG', 
                     type: 'PT_TAG',
                     defaultValue: 'master'
  }

  options { skipDefaultCheckout() } 

  stages {
    stage("Prepare") {
       steps {
          checkout scm: [
            $class: 'GitSCM',
            userRemoteConfigs: [[url: 'https://github.com/jenkinsci/git-parameter-plugin.git']],
            branches: [[name: "${params.TAG}"]], 
            poll: false
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
