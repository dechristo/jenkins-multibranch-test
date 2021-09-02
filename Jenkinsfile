pipeline {
  agent any
  
  options { skipDefaultCheckout() } 

  stages {
    stage("Prepare") {
       steps {
          checkout scm: [
            $class: 'GitSCM',
            userRemoteConfigs: [[url: 'https://github.com/jenkinsci/git-parameter-plugin.git']],
            branches: [[name: "**/tags/**"]], 
            refspec: '+refs/tags/*’:’refs/remotes/origin/tags/*',
            poll: false
        ]
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
