node {
    stage 'Clone the project'
    git 'https://github.com/4loopltd/JenkinsBuild.git'

    dir('.') {
        stage("Build") {
            sh "mvn clean install -DskipTests"
        }

        stage("Test") {
            try {
                sh "mvn test -Punit"
            } catch(err) {
                step([$class: 'JUnitResultArchiver', testResults:
                  '**/target/surefire-reports/*Test.xml'])
                throw err
            }
           step([$class: 'JUnitResultArchiver', testResults:
             '**/target/surefire-reports/*Test.xml'])
        }

    }
}