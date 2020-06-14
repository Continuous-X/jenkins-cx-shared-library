import com.continuousx.jenkins.features.maven.MavenFeatureImpl
import com.continuousx.jenkins.features.maven.MavenFeatureWrapperImpl
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildBuilder
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildConfig
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildImpl

def call(PipelineMavenBuildConfig pipelineConfig) {

    PipelineMavenBuildImpl pipelineMavenBuild = new PipelineMavenBuildBuilder(this)
            .withPipelineConfig(pipelineConfig)
            .build()

    pipeline {
        agent any
        options {
            timeout time: 30, unit: 'MINUTES'
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
                    expression { return pipelineMavenBuild.stageJenkinsConvertPluginsTxt.config.active }
                }
                steps {
                    milestone 20
                    script {
                        pipelineMavenBuild.stageJenkinsConvertPluginsTxt.runStage()
                    }
                }
            }

            stage('Build') {
                when {
                    expression { return pipelineConfig.getStageConfigMavenCompile().isActive() }
                }
                steps {
                    milestone 50
                    script {
                        log.info "run maven feature"
                        assert fileExists(file: MavenFeatureWrapperImpl.MVN_WRAPPER_FILENAME)
                        assert fileExists(file: MavenFeatureWrapperImpl.MVN_SETTINGS_XML)

                        new MavenFeatureWrapperImpl(this, pipelineConfig.getStageConfigMavenCompile().getLogLevelType()).run()
                        new MavenFeatureImpl(this, pipelineConfig.getStageConfigMavenCompile().getLogLevelType()).run()
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