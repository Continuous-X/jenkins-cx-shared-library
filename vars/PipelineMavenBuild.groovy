import com.continuousx.jenkins.features.maven.build.FeatureMavenBuildImpl
import com.continuousx.jenkins.features.maven.build.wrapper.FeatureMavenWrapperBuildImpl
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildBuilder
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildConfig
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildImpl
import com.continuousx.utils.github.BranchExpressionChecker

def call(final PipelineMavenBuildConfig pipelineConfig) {

    final PipelineMavenBuildImpl pipelineMavenBuild = new PipelineMavenBuildBuilder(this)
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

            stage('protection check') {
                when {
                    expression { pipelineMavenBuild.stageGHProtectionCheck.getStageConfig().isActive() }
                    expression { BranchExpressionChecker.checkBranchExpression(pipelineMavenBuild.stageGHProtectionCheck.getStageConfig().getAllowedBranch(), env.GIT_BRANCH) }
                }
                steps {
                    milestone 20
                    script {
                        pipelineMavenBuild.stageGHProtectionCheck.runStage()
                    }
                }
            }

            stage('Build') {
                when {
                    expression { pipelineMavenBuild.stageMavenInstall.getStageConfig().isActive() }
                    expression { BranchExpressionChecker.checkBranchExpression(pipelineMavenBuild.stageMavenInstall.getStageConfig().getAllowedBranch(), env.GIT_BRANCH) }
                }
                steps {
                    milestone 50
                    script {
                        pipelineMavenBuild.stageMavenInstall.runStage()
                    }
                }
            }

        }
        post {
            always {
                script {
                    pipelineMavenBuild.publishMetricOperating()
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