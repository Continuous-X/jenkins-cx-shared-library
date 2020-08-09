import com.continuousx.jenkins.features.jenkins.globallib.FeatureJenkinsGlobalLibVersionBuilder
import com.continuousx.jenkins.features.jenkins.globallib.FeatureJenkinsGlobalLibVersionConfig
import com.continuousx.jenkins.features.jenkins.globallib.FeatureJenkinsGlobalLibVersionImpl
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildBuilder
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildConfig
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildImpl
import com.continuousx.utils.github.BranchExpressionChecker
import sun.rmi.runtime.Log

def call(final PipelineMavenBuildConfig pipelineConfig) {

    final PipelineMavenBuildImpl pipelineMavenBuild = new PipelineMavenBuildBuilder(this)
            .withPipelineConfig(pipelineConfig)
            .build()

    pipeline {
        agent { label 'master' }
        options {
            timeout time: 30, unit: 'MINUTES'
            buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
            disableConcurrentBuilds()
        }
        stages {
            stage('Init') {
                steps {
                    script {
                        FeatureJenkinsGlobalLibVersionImpl featureJenkinsGlobalLibVersion = new FeatureJenkinsGlobalLibVersionBuilder(delegate)
                                .withFeatureConfig(new FeatureJenkinsGlobalLibVersionConfig())
                                .build()
                        featureJenkinsGlobalLibVersion.runFeature()
                    }
                }
            }

            stage('host scan') {
                when {
                    expression { pipelineMavenBuild.stageScanHost.getStageConfig().isActive() }
                    expression { BranchExpressionChecker.checkBranchExpression(pipelineMavenBuild.stageScanHost.getStageConfig().getAllowedBranch(), env.GIT_BRANCH) }
                }
                steps {
                    script {
                        pipelineMavenBuild.stageScanHost.runStage()
                    }
                }
            }

            stage('protection check') {
                when {
                    expression { pipelineMavenBuild.stageGHProtectionCheck.getStageConfig().isActive() }
                    expression { BranchExpressionChecker.checkBranchExpression(pipelineMavenBuild.stageGHProtectionCheck.getStageConfig().getAllowedBranch(), env.GIT_BRANCH) }
                }
                steps {
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