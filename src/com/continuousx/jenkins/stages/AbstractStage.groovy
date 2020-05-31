package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.jenkins.utils.JenkinsPluginCheck

abstract class AbstractStage implements Stage, Serializable {

    def m_jenkinsContext
    List<String> m_neededPlugins = []
    StageConfig m_config
    LogLevelType m_logLevel

    @SuppressWarnings('GroovyUntypedAccess')
    AbstractStage(final def jenkinsContext, final StageConfig config, final List<String> neededPlugins, final LogLevelType logLevel = LogLevelType.INFO) {
        Objects.nonNull(jenkinsContext)
        Objects.nonNull(config)
        Objects.nonNull(neededPlugins)
        this.m_jenkinsContext = jenkinsContext
        this.m_config = config
        this.m_logLevel = logLevel
        this.m_neededPlugins = neededPlugins
    }

    @SuppressWarnings('GroovyUntypedAccess')
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(m_jenkinsContext)
                .addInstalledPlugins()
                .addNeededPluginList(m_neededPlugins)
                .isPluginListInstalled()
    }

    abstract void runStage()

    StageConfig getConfig() {
        m_config
    }
}
