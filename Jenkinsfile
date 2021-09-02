pipeline {
  agent any

  parameters {
    gitParameter 
      name: 'TAG', 
      type: 'PT_TAG',
      defaultValue: 'master'
  }

  options { 
    skipDefaultCheckout true
  } 

  stages {
    stage("Prepare") {
       steps {
          checkout scm:[
            $class: 'GitSCM', 
            branches: [[name: "${params.TAG}"]], 
            doGenerateSubmoduleConfigurations: false, 
            extensions: [], 
            gitTool: 'Default', 
            submoduleCfg: [], 
            userRemoteConfigs: [[url: 'https://github.com/jenkinsci/git-parameter-plugin.git']]
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
