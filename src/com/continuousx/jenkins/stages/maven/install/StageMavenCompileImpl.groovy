package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.features.maven.MavenFeatureWrapperImpl
import com.continuousx.jenkins.stages.AbstractStage

class StageMavenCompileImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageMavenCompileImpl(final def jenkinsContext, final StageMavenCompileConfig config) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], config)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runStageImpl() {
        if (checkNeededPlugins()) {
            new MavenFeatureWrapperImpl(jenkinsContext, stageConfig.logLevelType()).runFeature()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }
}
