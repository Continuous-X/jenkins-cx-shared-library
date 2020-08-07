package com.continuousx.jenkins.stages.scanner

import com.cloudbees.groovy.cps.NonCPS

class StageScanHostBuilder {

    private def jenkinsContext
    private StageScanHostConfig stageConfig

    @SuppressWarnings('GroovyUntypedAccess')
    StageScanHostBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    @NonCPS
    StageScanHostBuilder withStageConfig(final StageScanHostConfig stageConfig) {
        Objects.nonNull(stageConfig)
        this.stageConfig = stageConfig
        this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageScanHostImpl build() {
        new StageScanHostImpl(jenkinsContext, stageConfig)
    }

}
