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
        NetworkInterface.getNetworkInterfaces().each {networkInterface ->
            logger.logInfo 'list inet adresses'
            networkInterface.getInetAddresses().each {inetAdresses ->
                logger.logInfo "adress: ${inetAdresses.getHostAddress()} / name: ${inetAdresses.getHostName()}"
            }
            logger.logInfo 'list interface adresses'
            networkInterface.getInterfaceAddresses().each {interfaceAdresses ->
                logger.logInfo "adress: ${interfaceAdresses.getAddress().toString()} / broadcast: ${interfaceAdresses.getBroadcast().toString()}"
            }
            logger.logInfo 'list sub network interfaces'
            networkInterface.getSubInterfaces().each {subInterfaces ->
                logger.logInfo "\tsub network interface: ${subInterfaces.getDisplayName()}"
                subInterfaces.getInetAddresses().each {subInetAdresses ->
                    logger.logInfo "adress: ${subInetAdresses.getHostAddress()} / name: ${subInetAdresses.getHostName()}"
                }
            }
        }
    }

}
