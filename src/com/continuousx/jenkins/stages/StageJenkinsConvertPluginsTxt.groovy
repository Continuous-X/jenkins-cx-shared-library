package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.maven.MavenFeaturePomImpl
import com.continuousx.jenkins.stages.config.StageConfigJenkinsConvertPluginsTxt

class StageJenkinsConvertPluginsTxt extends AbstractStage {
    StageJenkinsConvertPluginsTxt(def jenkinsContext, StageConfigJenkinsConvertPluginsTxt config, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                config, [
                    "workflow-basic-steps",
                    "maven-plugin"
                ],
                logLevel)
    }

    @Override
    void run() {
        if (checkNeededPlugins()) {
            new MavenFeaturePomImpl(jenkinsContext, config.logLevelType()).run()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }
}
