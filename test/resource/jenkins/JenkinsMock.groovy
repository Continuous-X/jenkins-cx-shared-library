package resource.jenkins

import hudson.PluginWrapper

interface JenkinsMock {

    List<PluginWrapper> plugins
    JenkinsMock getInstanceOrNull()
    JenkinsMock getPluginManager()

}
