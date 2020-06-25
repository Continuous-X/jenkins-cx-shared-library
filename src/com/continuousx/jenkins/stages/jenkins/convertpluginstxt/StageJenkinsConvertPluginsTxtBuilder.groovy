package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.cloudbees.groovy.cps.NonCPS

class StageJenkinsConvertPluginsTxtBuilder {

    private def m_jenkinsContext
    private StageJenkinsConvertPluginsTxtConfig m_stageConfig

    @SuppressWarnings('GroovyUntypedAccess')
    StageJenkinsConvertPluginsTxtBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.m_jenkinsContext = jenkinsContext
    }

    @NonCPS
    StageJenkinsConvertPluginsTxtBuilder withStageConfig(final StageJenkinsConvertPluginsTxtConfig stageConfig) {
        Objects.nonNull(stageConfig)
        this.m_stageConfig = stageConfig
        return this
    }

    @NonCPS
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageJenkinsConvertPluginsTxtImpl build() {
        new StageJenkinsConvertPluginsTxtImpl(m_jenkinsContext, m_stageConfig)
    }

}
