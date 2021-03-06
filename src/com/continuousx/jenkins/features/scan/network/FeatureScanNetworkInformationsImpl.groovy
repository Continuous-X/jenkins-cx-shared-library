package com.continuousx.jenkins.features.scan.network

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.scan.os.OS
import org.apache.commons.lang3.StringUtils

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
            String formatedMac = ''
            if (readedMac != null) {
                StringBuilder formatedMacBuilder = new StringBuilder()
                readedMac.encodeHex().toString().toCharArray().eachWithIndex { myChar, index ->
                    index.mod(2) == 0 ? formatedMacBuilder.append(myChar) : formatedMacBuilder.append(myChar).append(':')
                    logger.logInfo "index (${index}/${myChar}/${index.mod(2) == 0}): ${formatedMacBuilder}"
                }
                formatedMac = StringUtils.chop(formatedMacBuilder.toString())
            }
            logger.logInfo "list inet adresses (${networkInterface.getInetAddresses().toList().size()}) / MAC ${readedMac} / ${formatedMac}"
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

            logger.logInfo 'get info from host'
            String routerAdress = InetAddress.getByName('fritz.box').getHostAddress()
            String routerSubAdress = routerAdress.substring(0, routerAdress.lastIndexOf('.'))
            logger.logInfo "router adress: ${routerAdress}"
            logger.logInfo 'scan ip\'s'
            Socket socket = new Socket()
            1.step 256, 1, {segment ->
                final String searchIP = "http://${routerSubAdress}.${segment}"
                logger.logInfo "scan '${searchIP}'"
                try {
                    URLConnection connection = new URL(searchIP).openConnection()
                    connection.setConnectTimeout(1500)
                    connection.connect()
                    logger.logInfo "IP ${searchIP} in use - yeeeehaaaaa"
                } catch (Exception exception) {
                    logger.logError "IP ${searchIP} error - ${exception.message}"
                }
            }
            
        }
    }

}
