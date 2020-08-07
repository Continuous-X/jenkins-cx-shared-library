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
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        logger = new PipelineLogger(jenkinsContext)
        logger.logLevelType= LogLevelType.INFO
    }

    @NonCPS
    private loadInstalledPlugins(def jenkins = Jenkins.getInstanceOrNull()) {
        Objects.nonNull(jenkins)
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
        Objects.nonNull(pluginsList)
        this.pluginListInstalled = pluginsList
        return this
    }

    @NonCPS
    JenkinsPluginCheck addNeededPluginList(List<String> pluginsList) {
        Objects.nonNull(pluginsList)
        //assert pluginsList.size() > 0

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
        Objects.nonNull(pluginName)
        assert pluginName.length() > 0
        return pluginListInstalled.find{ it.equals(pluginName) }
    }

}
