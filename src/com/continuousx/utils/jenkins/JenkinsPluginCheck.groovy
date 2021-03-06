package com.continuousx.utils.jenkins

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger
import hudson.PluginWrapper
import jenkins.model.Jenkins

class JenkinsPluginCheck {

    private List<String> pluginListNeeded = []
    private List<String> pluginListInstalled = []

    private def jenkinsContext
    private PipelineLogger logger

    JenkinsPluginCheck(def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    @NonCPS
    JenkinsPluginCheck withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @NonCPS
    private loadInstalledPlugins(def jenkins = Jenkins.getInstanceOrNull()) {
        Objects.requireNonNull(jenkins)
        this.pluginListInstalled = mapInstalledPlugins(jenkins?.getPluginManager()?.getPlugins())
    }

    @NonCPS
    private List<String> mapInstalledPlugins(List<PluginWrapper> jenkinsPluginList = []) {
        List<String> installedList = []
        jenkinsPluginList.each { plugin ->
            installedList << plugin.getShortName()
        }
        return installedList
    }

    @NonCPS
    JenkinsPluginCheck addInstalledPlugins(List<String> pluginsList = loadInstalledPlugins()) {
        Objects.requireNonNull(pluginsList)
        this.pluginListInstalled = pluginsList
        return this
    }

    @NonCPS
    JenkinsPluginCheck addNeededPluginList(List<String> pluginsList) {
        Objects.requireNonNull(pluginsList)
        logger.logInfo "plugin list: ${pluginsList.size()}"
        this.pluginListNeeded = pluginsList
        return this
    }

    @NonCPS
    boolean isPluginListInstalled() {
        boolean checkInstalled = true
        pluginListNeeded.each { plugin ->
            if(!isPluginInstalled(plugin)){
                logger.logInfo "listet plugin not found ${plugin}"
                return checkInstalled = false
            }
            logger.logInfo "listet plugin found ${plugin}"
        }
        return checkInstalled
    }

    @NonCPS
    private boolean isPluginInstalled(String pluginName) {
        Objects.requireNonNull(pluginName)
        assert pluginName.length() > 0
        return pluginListInstalled.find{ it.equals(pluginName) }
    }

}
