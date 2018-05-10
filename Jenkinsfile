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