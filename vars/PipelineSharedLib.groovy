import com.continuousx.jenkins.pipeline.config.PipelineConfig

//@SuppressFBWarnings(value = 'SE_NO_SERIALVERSIONID')
def call(PipelineConfig config) {

    pipeline {
        agent any
        options {
            timeout time: 30, unit: 'MINUTES'
            timestamps()
            buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
            disableConcurrentBuilds()
        }
        stages {
            stage('Init') {
                agent { label 'master' }
                steps {
                    milestone 10
                    script {
                        log.info 'init this'
                    }
                }
            }

            stage('Build') {
                steps {
                    milestone 20
                    script {
                        log.info 'build this'
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
                    log.info 'pipeline end'
                }
            }
            success {
                script {
                    log.info 'pipeline ended success'
                }
            }
            failure {
                script {
                    log.info 'pipeline ended failed'
                    deleteDir()
                }
            }
        }
    }
}