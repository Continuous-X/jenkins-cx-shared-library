package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipeline.exceptions.JenkinsPluginNotInstalledException
import com.continuousx.jenkins.pipeline.utils.JenkinsPluginCheck

abstract class AbstractFeature implements Feature, Serializable{
    def jenkinsContext
    List<String> neededPlugins = []

    AbstractFeature(def jenkinsContext, List<String> neededPlugins) {
        Objects.nonNull(jenkinsContext)
        Objects.nonNull(neededPlugins)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        if(!checkNeededPlugins())
            throw new JenkinsPluginNotInstalledException("needed plugins not installed - please check ${neededPlugins.toString()}")
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

    abstract Feature prepare()
}
