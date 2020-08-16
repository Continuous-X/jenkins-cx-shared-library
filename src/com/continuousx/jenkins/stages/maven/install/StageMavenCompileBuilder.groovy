package com.continuousx.jenkins.stages.maven.install

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class StageMavenCompileBuilder {

    private def jenkinsContext
    private StageMavenCompileConfig stageConfig
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    StageMavenCompileBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    @NonCPS
    StageMavenCompileBuilder withStageConfig(final StageMavenCompileConfig stageConfig) {
        Objects.requireNonNull(stageConfig)
        this.stageConfig = stageConfig
        this
    }

    @NonCPS
    StageMavenCompileBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageMavenCompileImpl build() {
        new StageMavenCompileImpl(jenkinsContext, stageConfig, logger)
    }

}
