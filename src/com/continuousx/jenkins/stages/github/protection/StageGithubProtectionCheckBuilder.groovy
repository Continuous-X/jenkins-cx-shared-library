package com.continuousx.jenkins.stages.github.protection

import com.cloudbees.groovy.cps.NonCPS

class StageGithubProtectionCheckBuilder {

    private def jenkinsContext
    private StageGithubProtectionCheckConfig stageConfig

    @SuppressWarnings('GroovyUntypedAccess')
    StageGithubProtectionCheckBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    @NonCPS
    StageGithubProtectionCheckBuilder withStageConfig(final StageGithubProtectionCheckConfig stageConfig) {
        Objects.nonNull(stageConfig)
        this.stageConfig = stageConfig
        this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageGithubProtectionCheckImpl build() {
        new StageGithubProtectionCheckImpl(jenkinsContext, stageConfig)
    }

}
