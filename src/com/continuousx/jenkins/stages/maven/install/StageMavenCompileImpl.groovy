package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.features.Feature
import com.continuousx.jenkins.features.maven.build.wrapper.FeatureMavenWrapperBuildBuilder
import com.continuousx.jenkins.features.maven.build.wrapper.FeatureMavenWrapperBuildConfig
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.jenkins.stages.AbstractStage

class StageMavenCompileImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageMavenCompileImpl(final def jenkinsContext, final StageMavenCompileConfig stageConfig, final PipelineLogger logger) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], stageConfig, logger)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runStageImpl() {
        final StageMavenCompileConfig stageMavenCompileConfig = getStageConfig() as StageMavenCompileConfig
        final Feature mavenBuild = new FeatureMavenWrapperBuildBuilder(jenkinsContext)
                .withFeatureConfig(new FeatureMavenWrapperBuildConfig(
                        failOnError: stageMavenCompileConfig.isFailOnError()
                ))
                .withLogger(this.logger)
                .build()
        mavenBuild.runFeature()
    }

}
