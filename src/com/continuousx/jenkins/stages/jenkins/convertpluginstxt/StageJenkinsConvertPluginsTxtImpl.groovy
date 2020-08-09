package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.continuousx.jenkins.features.Feature
import com.continuousx.jenkins.features.maven.dependencies.FeatureMavenDepToJenkinsPluginsTxtBuilder
import com.continuousx.jenkins.features.maven.dependencies.FeatureMavenDepToJenkinsPluginsTxtConfig
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.jenkins.stages.AbstractStage

class StageJenkinsConvertPluginsTxtImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageJenkinsConvertPluginsTxtImpl(final def jenkinsContext, final StageJenkinsConvertPluginsTxtConfig config, final PipelineLogger logger) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], config, logger)
    }

    @Override
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    void runStageImpl() {
        final StageJenkinsConvertPluginsTxtConfig stageConfigMavenDepToJenkinsPluginsTxt = getStageConfig() as StageJenkinsConvertPluginsTxtConfig
        final Feature feature = new FeatureMavenDepToJenkinsPluginsTxtBuilder(jenkinsContext)
                .withFeatureConfig(new FeatureMavenDepToJenkinsPluginsTxtConfig(
                        failOnError: stageConfigMavenDepToJenkinsPluginsTxt.isFailOnError()
                ))
                .withLogger(this.logger)
                .build()
        feature.runFeature()
    }

}
