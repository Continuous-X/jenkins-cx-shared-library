package com.continuousx.jenkins.pipelines

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.config.PipelineConfigNextLevel
import com.continuousx.jenkins.stages.StageJenkinsConvertPluginsTxt

class PipelineNextLevel extends AbstractPipeline {

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyVariableCanBeFinal'])
    PipelineNextLevel(def jenkinsContext, PipelineConfigNextLevel config, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                config,
                ["workflow-basic-steps","maven-plugin"],
                logLevel)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void run() {
        pipeline {
            jenkinsContext.agent jenkinsContext.any
            jenkinsContext.options {
                jenkinsContext.timeout time: 30, unit: 'MINUTES'
                jenkinsContext.timestamps()
                jenkinsContext.buildDiscarder(jenkinsContext.logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
                jenkinsContext.disableConcurrentBuilds()
            }
            jenkinsContext.stages {
                jenkinsContext.stage('Init') {
                    jenkinsContext.agent { jenkinsContext.label 'master' }
                    jenkinsContext.steps {
                        jenkinsContext.milestone 10
                        jenkinsContext.script {
                            jenkinsContext.log.info 'init this'
                        }
                    }
                }

                jenkinsContext.stage('Convert DepToFile') {
                    jenkinsContext.when {
                        jenkinsContext.expression { config.getStageConfigJenkinsConvertPluginsTxt().isActive() }
                    }
                    jenkinsContext.steps {
                        jenkinsContext.milestone 20
                        jenkinsContext.script {
                            new StageJenkinsConvertPluginsTxt(this, config.getStageConfigJenkinsConvertPluginsTxt()).run()
                        }
                    }
                }
            }
        }
    }

    @Override
    PipelineConfigNextLevel getConfig() {
        return config
    }
}
