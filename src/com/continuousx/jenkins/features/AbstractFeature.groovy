package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipeline.exceptions.JenkinsPluginNotInstalledException
import com.continuousx.jenkins.pipeline.utils.JenkinsPluginCheck

class AbstractFeature implements Feature, Serializable{
    private def jenkinsContext
    private List<String> neededPlugins = []

    AbstractFeature(def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        if(!checkNeededPlugins())
            throw new JenkinsPluginNotInstalledException("Plugins 4 Maven Wrapper not installed")
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
}
