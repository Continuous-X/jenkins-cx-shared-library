package com.continuousx.jenkins.stages.scanner

import com.continuousx.jenkins.features.Feature
import com.continuousx.jenkins.features.scan.os.FeatureScanOsInformationsBuilder
import com.continuousx.jenkins.features.scan.os.FeatureScanOsInformationsConfig
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.jenkins.stages.AbstractStage
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileConfig

class StageScanHostImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageScanHostImpl(final def jenkinsContext, final StageScanHostConfig stageConfi, final PipelineLogger logger) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], stageConfig, logger)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runStageImpl() {
        final StageScanHostConfig stageScanHostConfig = getStageConfig() as StageScanHostConfig
        final Feature scanOS = new FeatureScanOsInformationsBuilder(jenkinsContext)
                .withFeatureConfig(new FeatureScanOsInformationsConfig(
                        failOnError: stageScanHostConfig.isFailOnError()
                ))
                .withLogger(this.logger)
                .build()
        scanOS.runFeature()
    }
}
