import edu.umd.cs.findbugs.annotations.SuppressFBWarnings

@SuppressFBWarnings(value = 'SE_NO_SERIALVERSIONID')
def call(Map parameters = [:]) {

    def script = parameters?.script

    pipeline {
        agent any
        options {
            timeout time: 30, unit: 'MINUTES'
            timestamps()
            buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
            skipDefaultCheckout()
            disableConcurrentBuilds()
        }
        stages {
            stage('Init') {
                agent { label 'master'}
                steps {
                    milestone 10
                    echo 'init this'
                }
            }

            stage('Build') {
                steps {
                    milestone 20
                    echo 'build this'
                }
            }
        }
        post {
            always {
                echo 'pipeline end'
            }
            success {
                echo 'pipeline ended success'
            }
            failure {
                echo 'pipeline ended failed'
                deleteDir()
            }
        }
    }
}