package com.continuousx.jenkins.features.scan.network

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.scan.os.OS

class FeatureScanNetworkInformationsImpl extends AbstractFeature {


    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureScanNetworkInformationsImpl(final def jenkinsContext, final FeatureScanNetworkInformationsConfig featureConfig, final PipelineLogger logger) {
        super(jenkinsContext,
                [],
                featureConfig,
                logger)
    }

    @Override
    void runFeatureImpl() {
        logger.logInfo "os.name: ${OS.OS_NAME}"
        logger.logInfo "user.name: ${OS.USER_NAME}"
    }

}
