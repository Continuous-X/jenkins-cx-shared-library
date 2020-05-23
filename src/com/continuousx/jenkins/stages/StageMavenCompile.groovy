package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.maven.MavenFeatureWrapperImpl
import com.continuousx.jenkins.stages.config.StageConfigMavenCompile

class StageMavenCompile extends AbstractStage {
    StageMavenCompile(def jenkinsContext, StageConfigMavenCompile config, LogLevelType logLevel = LogLevelType.INFO) {
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
            new MavenFeatureWrapperImpl(jenkinsContext, config.logLevelType()).run()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }
}
