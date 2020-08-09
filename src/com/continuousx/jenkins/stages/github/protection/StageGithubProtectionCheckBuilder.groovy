package com.continuousx.jenkins.stages.github.protection

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class StageGithubProtectionCheckBuilder {

    private def jenkinsContext
    private StageGithubProtectionCheckConfig stageConfig
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    StageGithubProtectionCheckBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    @NonCPS
    StageGithubProtectionCheckBuilder withStageConfig(final StageGithubProtectionCheckConfig stageConfig) {
        Objects.nonNull(stageConfig)
        this.stageConfig = stageConfig
        this
    }

    @NonCPS
    StageGithubProtectionCheckBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageGithubProtectionCheckImpl build() {
        new StageGithubProtectionCheckImpl(jenkinsContext, stageConfig, logger)
    }

}
