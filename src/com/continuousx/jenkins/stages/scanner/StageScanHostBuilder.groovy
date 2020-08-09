package com.continuousx.jenkins.stages.scanner

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class StageScanHostBuilder {

    private def jenkinsContext
    private StageScanHostConfig stageConfig
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    StageScanHostBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    @NonCPS
    StageScanHostBuilder withStageConfig(final StageScanHostConfig stageConfig) {
        Objects.nonNull(stageConfig)
        this.stageConfig = stageConfig
        this
    }

    @NonCPS
    StageScanHostBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageScanHostImpl build() {
        new StageScanHostImpl(jenkinsContext, stageConfig, logger)
    }

}
