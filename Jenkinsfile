pipeline {
    agent any

    environment {
        IMAGE = "it2022134/ds-exc-2024:latest"
    }

    stages {
        stage('Checkout') {
            steps {
		        git(
      	            url: 'https://github.com/AthosExarchou/DevOps.git',
      		        branch: 'test'
    		    )
            }
        }

        stage('Maven Build') {
            steps {
                dir('ds-exc-2024') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE ./ds-exc-2024'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-token', variable: 'DOCKER_PASSWORD')]) {
                    sh '''
                        echo $DOCKER_PASSWORD | docker login -u it2022134 --password-stdin
                        docker push $IMAGE
                    '''
                }
            }
        }

        stage('Install kubectl') {
            steps {
                sh '''
                    sudo snap install kubectl --classic
                    kubectl version --client
                '''
            }
        }

        stage('Deploy DB') {
            steps {
                sshagent(['azure-ssh-cred-id']) {
                    sh '''
                        cd realestate-monorepo/k8s/db
                        sudo microk8s.kubectl apply -f .
                    '''
                }
            }
        }

        stage('Deploy APP') {
            steps {
                sshagent(['azure-ssh-cred-id']) {
                    sh '''
                        cd realestate-monorepo/k8s/app
                        sudo microk8s.kubectl apply -f .
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                    sudo microk8s.kubectl set image deployment/app-deployment \
                      app=$IMAGE --namespace=default

                    sudo microk8s.kubectl rollout status deployment/app-deployment \
                      --namespace=default
                '''
            }
        }

        stage('Debug Checkout') {
            steps {
                sh '''
                    echo "Current branch:" $(git rev-parse --abbrev-ref HEAD)
                    echo "Available branches:"
                    git branch -a
                    echo "Workspace contents:"
                    ls -R .
                '''
            }
        }
    }
}