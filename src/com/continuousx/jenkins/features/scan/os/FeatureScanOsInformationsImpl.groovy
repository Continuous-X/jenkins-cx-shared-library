package com.continuousx.jenkins.features.scan.os

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.scan.os.OS

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
        logger.logInfo "os.name: ${OS.OS_NAME}"
        logger.logInfo "user.name: ${OS.USER_NAME}"
    }

}
