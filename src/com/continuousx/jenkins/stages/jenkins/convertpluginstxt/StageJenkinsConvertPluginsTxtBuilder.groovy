package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class StageJenkinsConvertPluginsTxtBuilder {

    private def jenkinsContext
    private StageJenkinsConvertPluginsTxtConfig m_stageConfig
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    StageJenkinsConvertPluginsTxtBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    @NonCPS
    StageJenkinsConvertPluginsTxtBuilder withStageConfig(final StageJenkinsConvertPluginsTxtConfig stageConfig) {
        Objects.nonNull(stageConfig)
        this.m_stageConfig = stageConfig
        return this
    }

    @NonCPS
    StageJenkinsConvertPluginsTxtBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageJenkinsConvertPluginsTxtImpl build() {
        new StageJenkinsConvertPluginsTxtImpl(jenkinsContext, m_stageConfig, logger)
    }

}
