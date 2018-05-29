node {
    stage('Build') { // for display purposes
        echo "${workspace}"
        //checkout code
        checkout scm

        microserviceProjects = readDir("${workspace}")
        println microserviceProjects
        def excludeProjects = ["img-folder","microservice.auth.service","microservice.boot.admin", "microservice.cart.service"]
        microserviceProjects.each { projectName ->
            if(!excludeProjects.contains(projectName))
                sh "mvn -f ${projectName}/pom.xml dockerfile:build dockerfile:push"
        }
    }

    stage('Deploy to Kubernetes') {
        microserviceProjects = readDir("${workspace}")
        microserviceProjects.each { projectName ->
            if(projectName == "microservice.config")
                sh "/usr/local/bin/kubectl create -f ${projectName}/config-server-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"

            if(projectName == "microservice.customer.service"){
                sh "/usr/local/bin/kubectl create -f ${projectName}/mysql-customer-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"
                sh "/usr/local/bin/kubectl create -f ${projectName}/customer-service-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"
            }

            if(projectName == "microservice.discovery.eureka")
                sh "/usr/local/bin/kubectl create -f ${projectName}/discovery-eureka-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"

            if(projectName == "microservice.hystrix.dashboard")
                sh "/usr/local/bin/kubectl create -f ${projectName}/hystrix-dashboard-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"

            if(projectName == "microservice.hystrix.turbine")
                sh "/usr/local/bin/kubectl create -f ${projectName}/kystrix-turbine-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"

            if(projectName == "microservice.order.service"){
                sh "/usr/local/bin/kubectl create -f ${projectName}/mysql-order-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"
                sh "/usr/local/bin/kubectl create -f ${projectName}/order-service-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"
            }

            if(projectName == "microservice.product.service"){
                sh "/usr/local/bin/kubectl create -f ${projectName}/mysql-product-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"
                sh "/usr/local/bin/kubectl create -f ${projectName}/product-service-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"
            }

            if(projectName == "microservice.zipkin.service")
                sh "/usr/local/bin/kubectl create -f ${projectName}/zipkin-service-deployment.yaml  --kubeconfig=/var/lib/jenkins/kubeconfig"

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