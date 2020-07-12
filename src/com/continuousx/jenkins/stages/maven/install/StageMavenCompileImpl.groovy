package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.features.maven.build.wrapper.FeatureMavenWrapperBuildBuilder
import com.continuousx.jenkins.features.maven.build.wrapper.FeatureMavenWrapperBuildConfig
import com.continuousx.jenkins.features.maven.build.wrapper.FeatureMavenWrapperBuildImpl
import com.continuousx.jenkins.stages.AbstractStage

class StageMavenCompileImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageMavenCompileImpl(final def jenkinsContext, final StageMavenCompileConfig stageConfig) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], stageConfig)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runStageImpl() {
        if (checkNeededPlugins()) {
            final FeatureMavenWrapperBuildImpl mavenBuild = new FeatureMavenWrapperBuildBuilder(jenkinsContext)
                    .withFeatureConfig(new FeatureMavenWrapperBuildConfig(
                            failOnError:stageConfig.failOnError,
                            logLevelType:stageConfig.logLevelType
                    )).build()
            mavenBuild.runFeature()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }

}
