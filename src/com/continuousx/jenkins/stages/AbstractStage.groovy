package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.utils.JenkinsPluginCheck
import com.continuousx.jenkins.stages.config.StageConfig

abstract class AbstractStage {
    def jenkinsContext
    List<String> neededPlugins = []
    StageConfig config
    LogLevelType logLevel

    AbstractStage(def jenkinsContext, StageConfig config, List<String> neededPlugins, LogLevelType logLevel = LogLevelType.INFO) {
        Objects.nonNull(jenkinsContext)
        Objects.nonNull(config)
        Objects.nonNull(neededPlugins)
        this.jenkinsContext = jenkinsContext
        this.config = config
        this.logLevel = logLevel
        this.neededPlugins = neededPlugins
    }

    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .and()
                .addNeededPluginList(neededPlugins)
                .and()
                .isPluginListInstalled()
    }

    abstract void run()
}
