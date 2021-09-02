pipeline {
  agent any

  options { skipDefaultCheckout() } 

  stages {
    stage("Prepare") {
      //  steps {
      //     checkout scm:[
      //       $class: 'GitSCM', 
      //       branches: [[name: "${params.TAG}"]], 
      //       doGenerateSubmoduleConfigurations: false, 
      //       extensions: [], 
      //       gitTool: 'Default', 
      //       submoduleCfg: [], 
      //       userRemoteConfigs: [[url: 'https://github.com/jenkinsci/git-parameter-plugin.git']]
      //     ]
      //  }

       steps {
          checkout scm: [
            $class: 'GitSCM',
            userRemoteConfigs: [[url: 'YOUR_GIT_REPO_URL.git', credentialsId: 'YOUR_GIT_CREDENTIALS_ID' ]], 
            branches: [[name: 'refs/tags/${TAG}']]],
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
