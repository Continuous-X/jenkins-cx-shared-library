package com.continuousx.jenkins.stages.maven.install

import com.cloudbees.groovy.cps.NonCPS

class StageMavenCompileBuilder {

    private def jenkinsContext
    private StageMavenCompileConfig stageConfig

    @SuppressWarnings('GroovyUntypedAccess')
    StageMavenCompileBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    @NonCPS
    StageMavenCompileBuilder withStageConfig(final StageMavenCompileConfig stageConfig) {
        Objects.nonNull(stageConfig)
        this.stageConfig = stageConfig
        this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageMavenCompileImpl build() {
        new StageMavenCompileImpl(jenkinsContext, stageConfig)
    }

}
