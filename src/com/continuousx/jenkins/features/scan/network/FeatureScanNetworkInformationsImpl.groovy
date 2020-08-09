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
        logger.logInfo "IP: ${InetAddress.getLocalHost()}"
        logger.logInfo "IP(4): ${Inet4Address.getLocalHost()}"
        logger.logInfo "IP(6): ${Inet6Address.getLocalHost()}"
    }

}
