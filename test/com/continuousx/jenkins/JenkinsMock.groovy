package com.continuousx.jenkins

import hudson.PluginManager
import hudson.PluginWrapper

import java.util.jar.Manifest

class JenkinsMock {

    List<PluginWrapper> plugins = [

    ]


    JenkinsMock getInstanceOrNull() {
        return this
    }
    JenkinsMock getPluginManager() {
        return this
    }

    List<PluginWrapper> getPlugins() {
        List<PluginWrapper> plugins = []
        PluginWrapper plugin1 = new PluginWrapper( null, null, null, null, null, null, null, null)
        plugin1.shortName = "test-plugin-1"
        plugins << plugin1
    }
}
