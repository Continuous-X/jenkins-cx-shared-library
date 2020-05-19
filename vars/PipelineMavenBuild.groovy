import com.continuousx.jenkins.pipelines.config.PipelineConfigMavenBuild
import com.continuousx.jenkins.stages.StageJenkinsConvertPluginsTxt

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
                when {
                    expression { return config.stageConfigJenkinsConvertPluginsTxt.isActive() }
                }
                steps {
                    milestone 20
                    script {
                        new StageJenkinsConvertPluginsTxt(this, config.getStageConfigJenkinsConvertPluginsTxt()).run()
                    }
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