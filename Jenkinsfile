pipeline {
    agent any
    stages {
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
        
        stage('deploy') {
            steps {
                sh './deploy/deplay_fake.sh'
            }
        }
    }
}
