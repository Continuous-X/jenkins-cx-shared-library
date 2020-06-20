package com.continuousx.jenkins.stages.maven.install

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.stages.jenkins.convertpluginstxt.StageJenkinsConvertPluginsTxtConfig
import com.continuousx.jenkins.stages.jenkins.convertpluginstxt.StageJenkinsConvertPluginsTxtImpl

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
        return this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageMavenCompileImpl build() {
        new StageMavenCompileImpl(jenkinsContext, stageConfig, stageConfig.logLevelType)
    }

}
