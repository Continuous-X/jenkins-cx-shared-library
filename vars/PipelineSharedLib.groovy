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
                agent { label 'master' }
                steps {
                    milestone 10
                    script {
                        Log.info 'init this'
                    }
                }
            }

            stage('Build') {
                steps {
                    milestone 20
                    script {
                        Log.info 'build this'
                    }
                }
            }

            stage('Stages') {
                steps {
                    milestone 50
                    StageConvertMvnDepToJenkinsPluginsTxt(config)
                }
            }

        }
        post {
            always {
                script {
                    Log.info 'pipeline end'
                }
            }
            success {
                script {
                    Log.info 'pipeline ended success'
                }
            }
            failure {
                script {
                    Log.info 'pipeline ended failed'
                    deleteDir()
                }
            }
        }
    }
}