package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.maven.MavenFeatureWrapperImpl
import com.continuousx.jenkins.stages.AbstractStage

class StageMavenCompileImpl extends AbstractStage {

    StageMavenCompileImpl(def jenkinsContext, StageMavenCompileConfig config, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                config, [
                    "workflow-basic-steps",
                    "maven-plugin"
                ],
                logLevel)
    }

    @Override
    void runStageImpl() {
        if (checkNeededPlugins()) {
            new MavenFeatureWrapperImpl(jenkinsContext, stageConfig.logLevelType()).runFeature()
        } else {
            jenkinsContext.log.error("check needed plugins: ${m_neededPlugins}")
        }
    }
}
