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
        logger.logInfo "network methods: ${NetworkInterface.getMethods()}"
        NetworkInterface.getNetworkInterfaces().each {networkInterface ->
            byte[] readedMac = networkInterface.getHardwareAddress()
            StringBuilder formatedMac = new StringBuilder()
            if (readedMac != null) {
                readedMac.encodeHex().toString().toCharArray().eachWithIndex { myChar, index ->
                    index.mod(2) == 0 && index < 11 ? formatedMac.append(myChar) : formatedMac.append(myChar).append('-')
                    logger.logInfo "index (${index}/${myChar}/${index.mod(2) == 0}): ${formatedMac}"
                }
            }
            logger.logInfo "list inet adresses (${networkInterface.getInetAddresses().toList().size()}): ${readedMac} / ${formatedMac}"
            networkInterface.getInetAddresses().each {inetAdresses ->
                logger.logInfo """
adress: ${inetAdresses.getAddress()} 
host adress: ${inetAdresses.getHostAddress()} 
host name: ${inetAdresses.getHostName()}
canonical host name: ${inetAdresses.getCanonicalHostName()}
properties: ${inetAdresses.getProperties()} 

"""
            }
            logger.logInfo "list interface adresses (${networkInterface.getInterfaceAddresses().size()})"
            networkInterface.getInterfaceAddresses().each {interfaceAdresses ->
                    logger.logInfo """
adress: ${interfaceAdresses.getAddress()}
    adress: ${interfaceAdresses.getAddress().getAddress()}
    host adress: ${interfaceAdresses.getAddress().getHostAddress()}
    host name: ${interfaceAdresses.getAddress().getHostName()}
    host adress: ${interfaceAdresses.getAddress().getHostAddress()}
    canonical host adress: ${interfaceAdresses.getAddress().getCanonicalHostName()}
broadcast: ${interfaceAdresses.getBroadcast()}
properties: ${interfaceAdresses.getProperties()}

"""
            }
            logger.logInfo "list sub network interfaces (${networkInterface.getSubInterfaces().toList().size()})"
            networkInterface.getSubInterfaces().each {subInterfaces ->
                logger.logInfo "\tsub network interface: ${subInterfaces.getDisplayName()}"
                subInterfaces.getInetAddresses().each {subInetAdresses ->
                    logger.logInfo "adress: ${subInetAdresses.getHostAddress()} / name: ${subInetAdresses.getHostName()}"
                }
            }
        }
    }

}
