package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.maven.MavenFeatureWrapperImpl
import com.continuousx.jenkins.stages.AbstractStage

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
    void runStage() {
        if (checkNeededPlugins()) {
            new MavenFeatureWrapperImpl(m_jenkinsContext, m_config.logLevelType()).run()
        } else {
            m_jenkinsContext.log.error("check needed plugins: ${m_neededPlugins}")
        }
    }
}
