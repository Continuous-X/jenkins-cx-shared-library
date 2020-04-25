package com.continuousx.jenkins.pipeline.utils

import hudson.PluginWrapper
import jenkins.model.Jenkins

class JenkinsPluginCheck {

    private List<String> pluginCheckList
    private List<PluginWrapper> jenkinsPluginList

    JenkinsPluginCheck addPluginList(List<String> pluginsList) {
        Objects.nonNull(pluginsList)
        assert pluginsList.size() > 0
        this.pluginCheckList = pluginsList
        this.jenkinsPluginList = Jenkins.getInstanceOrNull().getPluginManager().getPlugins()
        return this
    }

    JenkinsPluginCheck and() {
        return this
    }

    public boolean isPluginListInstalled() {
        pluginCheckList.each { plugin ->
            if(!isPluginInstalled(plugin)){
                return false
            }
        }
        return true
    }

    public boolean isPluginInstalled(String pluginName) {
        jenkinsPluginList.each { plugin ->
            if(plugin.getShortName().equals(pluginName)){
                return true
            }
        }
        return false
    }
}
