node {
    def mvnHome
    stage('Build') { // for display purposes
        echo ${workspace}

        microserviceProjects = readDir("${workspace}")
        println microserviceProjects

        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
        //sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
    }

}

def readDir(rootPath) {
    def dirsl = []
    new File(rootPath).eachDir()
            {
                dirs ->
                    println dirs.getName()
                    if (!dirs.getName().startsWith('.')) {
                        dirsl.add(dirs.getName())
                    }
            }
    dirsl
}