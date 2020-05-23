package com.continuousx.jenkins.pipelines

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.config.PipelineConfig
import com.continuousx.jenkins.pipelines.utils.JenkinsPluginCheck

abstract class AbstractPipeline implements Pipeline, Serializable {
    def jenkinsContext
    List<String> neededPlugins = []
    PipelineConfig config
    LogLevelType logLevel

    AbstractPipeline(def jenkinsContext, PipelineConfig config, List<String> neededPlugins, LogLevelType logLevel = LogLevelType.INFO) {
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
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

}
