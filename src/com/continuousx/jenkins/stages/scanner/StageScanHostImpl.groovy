package com.continuousx.jenkins.stages.scanner

import com.continuousx.jenkins.features.Feature
import com.continuousx.jenkins.features.scan.os.FeatureScanOsInformationsBuilder
import com.continuousx.jenkins.features.scan.os.FeatureScanOsInformationsConfig
import com.continuousx.jenkins.stages.AbstractStage

class StageScanHostImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageScanHostImpl(final def jenkinsContext, final StageScanHostConfig stageConfig) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], stageConfig)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runStageImpl() {
        final Feature scanOS = new FeatureScanOsInformationsBuilder(jenkinsContext)
                .withFeatureConfig(new FeatureScanOsInformationsConfig(
                        failOnError: stageConfig.failOnError,
                        logLevelType: stageConfig.logLevelType
                )).build()
        scanOS.runFeature()
    }
}
