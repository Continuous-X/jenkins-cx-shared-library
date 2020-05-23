import com.continuousx.jenkins.features.maven.MavenFeatureImpl
import com.continuousx.jenkins.features.maven.MavenFeatureWrapperImpl
import com.continuousx.jenkins.stages.StageJenkinsConvertPluginsTxt

def call(com.continuousx.jenkins.pipelines.PipelineMavenBuild mavenPipeline) {

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
                    expression { return mavenPipeline.getConfig().getStageConfigJenkinsConvertPluginsTxt().isActive() }
                }
                steps {
                    milestone 20
                    script {
                        new StageJenkinsConvertPluginsTxt(this, mavenPipeline.getConfig().getStageConfigJenkinsConvertPluginsTxt()).run()
                    }
                }
            }

            stage('Build') {
                when {
                    expression { return mavenPipeline.getConfig().getStageConfigMavenCompile().isActive() }
                }
                steps {
                    milestone 50
                    script {
                        log.info "run maven feature"
                        assert fileExists(file: MavenFeatureWrapperImpl.MVN_WRAPPER_FILENAME)
                        assert fileExists(file: MavenFeatureWrapperImpl.MVN_SETTINGS_XML)

                        new MavenFeatureWrapperImpl(this, mavenPipeline.getConfig().getStageConfigMavenCompile().getLogLevelType()).run()
                        new MavenFeatureImpl(this, mavenPipeline.getConfig().getStageConfigMavenCompile().getLogLevelType()).run()
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