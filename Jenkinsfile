pipeline {
    agent any
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
                    sh 'docker build -t devsahamerlin/listingrestapi .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'BuildNext@Give#Up', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u devsahamerlin -p ${dockerhubpwd}'

}
                   sh 'docker push devsahamerlin/listingrestapi'
                }
            }
        }
    }
}