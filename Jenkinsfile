pipeline {
    agent any
    stages {
        
         stage('deploy') {
            steps {
                sh 'ls'
                sh './deploy/deplay_fake.sh'
            }
        }
        stage('build') {
            steps {
                sh 'docker build .'
            }
        }
        
        stage('test') {
            steps {
                echo 'hello from test'
            }
        }
       
       
    }
}
