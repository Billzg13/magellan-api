pipeline {
    agent any
    stages {
        
         stage('deploy') {
            steps {
                sh 'ls'
                sh 'chmod 0755 ./deploy/deplay_fake.sh'
                sh 'chmod 0400 ./deploy/ptuxiakiKey.pem'
                sh 'chmod 0755 ./deploy/deploy_script.sh'
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
