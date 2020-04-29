package com.continuousx.jenkins.pipeline.utils

import com.cloudbees.groovy.cps.NonCPS
import hudson.PluginWrapper
import jenkins.model.Jenkins

class JenkinsPluginCheck {

    private List<String> pluginCheckList
    private List<PluginWrapper> jenkinsPluginList

    private def jenkinsContext

    @NonCPS
    JenkinsPluginCheck addPluginList(List<String> pluginsList, def jenkinsContext) {
        Objects.nonNull(pluginsList)
        Objects.nonNull(jenkinsContext)
        assert pluginsList.size() > 0
        this.pluginCheckList = pluginsList
        this.jenkinsPluginList = Jenkins.getInstanceOrNull().getPluginManager().getPlugins()
        this.jenkinsContext = jenkinsContext
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
                jenkinsContext.echo "found ${pluginName} -> ${plugin.getShortName()} / ${plugin.getDisplayName()} / ${plugin.getVersion()}"
                return true
            }
        }
        return false
    }
}
