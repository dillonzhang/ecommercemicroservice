node {
    stage('Build') { // for display purposes
        echo "${workspace}"
        //checkout code
        checkout scm

        microserviceProjects = readDir("${workspace}")
        println microserviceProjects
        microserviceProjects.each { projectName ->
            sh "mvn -f ${projectName}/pom.xml dockerfile:build dockerfile:push"
        }
    }

    stage('Deploy to Kubernetes') {
        microserviceProjects = readDir("${workspace}")
        microserviceProjects.each { projectName ->
            if(projectName == "microservice.config")
                sh "/usr/local/bin/kubectl create -f ${projectName}/config-server-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"
        }
    }

}

@NonCPS
def readDir(rootPath) {
    def dirsl = []
    new File(rootPath).eachDir()
    {
        dirs ->
            if (!dirs.getName().startsWith('.')) {
                dirsl.add(dirs.getName())
            }
    }
    dirsl
}