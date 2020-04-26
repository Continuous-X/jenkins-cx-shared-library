package com.continuousx.jenkins.pipeline.utils

import com.cloudbees.groovy.cps.NonCPS
import hudson.PluginWrapper
import jenkins.model.Jenkins

class JenkinsPluginCheck {

    private List<String> pluginCheckList
    private List<PluginWrapper> jenkinsPluginList

    @NonCPS
    JenkinsPluginCheck addPluginList(List<String> pluginsList) {
        Objects.nonNull(pluginsList)
        assert pluginsList.size() > 0
        this.pluginCheckList = pluginsList
        this.jenkinsPluginList = Jenkins.getInstanceOrNull().getPluginManager().getPlugins()
        return this
    }

    @NonCPS
    JenkinsPluginCheck and() {
        return this
    }

    @NonCPS
    public boolean isPluginListInstalled() {
        pluginCheckList.each { plugin ->
            if(!isPluginInstalled(plugin)){
                return false
            }
        }
        return true
    }

    @NonCPS
    private boolean isPluginInstalled(String pluginName) {
        jenkinsPluginList.each { plugin ->
            if(plugin.getShortName().equals(pluginName)){
                println "found ${pluginName} -> ${plugin.getShortName()} / ${plugin.getDisplayName()} / ${plugin.getVersion()}"
                return true
            }
        }
        return false
    }
}
