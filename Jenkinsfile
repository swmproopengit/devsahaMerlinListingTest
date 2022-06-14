pipeline {
    agent any
    tools{
        maven 'maven_3_5_0'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/swmproopengit/devsahaMerlinListingTest']]])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t devsahamerlin/ListingRestAPI .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'BuildNext@Give#Up', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u devsahamerlin -p ${dockerhubpwd}'

}
                   sh 'docker push devsahamerlin/ListingRestAPI'
                }
            }
        }
    }
}