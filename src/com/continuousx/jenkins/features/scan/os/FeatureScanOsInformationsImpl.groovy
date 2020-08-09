package com.continuousx.jenkins.features.scan.os

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureScanOsInformationsImpl extends AbstractFeature {


    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureScanOsInformationsImpl(final def jenkinsContext, final FeatureScanOsInformationsConfig featureConfig, final PipelineLogger logger) {
        super(jenkinsContext,
                [],
                featureConfig,
                logger)
    }

    @Override
    void runFeatureImpl() {
        logger.logInfo "IP: ${InetAddress.getLocalHost()}"
    }

}
