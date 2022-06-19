pipeline {
    agent any
    stages{
        stage('Check jdk version'){
            steps{
                script{
                    sh 'java -version'
                }
            }
        }
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/swmproopengit/devsahaMerlinListingTest']]])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t devsahamerlin/listingrestapi .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'devdockerhub-pwd', variable: 'devdockerhubpwd')]) {
                        sh 'docker login -u devsahamerlin --password-stdin ${devdockerhubpwd}'
                    }
                   sh 'docker push devsahamerlin/listingrestapi'
                }
            }
        }
    }
}