package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.features.maven.build.FeatureMavenBuildBuilder
import com.continuousx.jenkins.features.maven.build.FeatureMavenBuildConfig
import com.continuousx.jenkins.features.maven.build.FeatureMavenBuildImpl
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
            final FeatureMavenBuildImpl mavenBuild = new FeatureMavenBuildBuilder(jenkinsContext)
                    .withFeatureConfig(new FeatureMavenBuildConfig(
                            failOnError:getStageConfig().isFailOnError(),
                            logLevelType:getStageConfig().logLevelType()
                    )).build()
            mavenBuild.runFeature()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }
}
