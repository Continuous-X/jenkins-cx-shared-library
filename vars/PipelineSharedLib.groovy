import com.continuousx.jenkins.pipeline.config.PipelineConfig

//@SuppressFBWarnings(value = 'SE_NO_SERIALVERSIONID')
def call(PipelineConfig config) {

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
                    Log.info('init this')
                }
            }

            stage('Build') {
                steps {
                    milestone 20
                    Log.info 'build this'
                }
            }
        }
        post {
            always {
                Log.info 'pipeline end'
            }
            success {
                Log.info 'pipeline ended success'
            }
            failure {
                Log.info 'pipeline ended failed'
                deleteDir()
            }
        }
    }
}