package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

class StageJenkinsConvertPluginsTxtBuilder {

    private def m_jenkinsContext
    private StageJenkinsConvertPluginsTxtConfig m_stageConfig

    @SuppressWarnings('GroovyUntypedAccess')
    StageJenkinsConvertPluginsTxtBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.m_jenkinsContext = jenkinsContext
    }

    StageJenkinsConvertPluginsTxtBuilder withStageConfig(final StageJenkinsConvertPluginsTxtConfig stageConfig) {
        Objects.nonNull(stageConfig)
        this.m_stageConfig = stageConfig
        return this
    }

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    StageJenkinsConvertPluginsTxtImpl build() {
        new StageJenkinsConvertPluginsTxtImpl(m_jenkinsContext, m_stageConfig, m_stageConfig.logLevelType)
    }

}
