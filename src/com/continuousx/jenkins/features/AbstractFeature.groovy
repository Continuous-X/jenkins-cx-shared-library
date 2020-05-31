package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.jenkins.utils.JenkinsPluginCheck

abstract class AbstractFeature implements Feature, Serializable{
    def jenkinsContext
    List<String> neededPlugins = []
    LogLevelType logLevel = LogLevelType.INFO

    AbstractFeature(def jenkinsContext, List<String> neededPlugins, LogLevelType logLevel) {
        Objects.nonNull(jenkinsContext)
        Objects.nonNull(neededPlugins)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.logLevel = logLevel
    }

    @NonCPS
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

    @NonCPS
    abstract Feature run()
}
