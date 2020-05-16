package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.utils.JenkinsPluginCheck

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
    @Override
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .and()
                .addNeededPluginList(neededPlugins)
                .and()
                .isPluginListInstalled()
    }

    @NonCPS
    abstract Feature prepare()
}
