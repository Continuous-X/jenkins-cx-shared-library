import com.continuousx.jenkins.pipelines.config.PipelineConfigMavenBuild

def call(PipelineConfigMavenBuild config) {

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

            stage('Convert DepToFile') {
                steps {
                    milestone 20
                    StageConvertMvnDepToJenkinsPluginsTxt(config)
                }
            }

            stage('Build') {
                steps {
                    milestone 50
                    script {
                        StageMvnWrapperBuild(config)
                    }
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