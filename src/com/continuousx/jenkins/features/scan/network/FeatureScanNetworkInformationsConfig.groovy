package com.continuousx.jenkins.features.scan.network

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType

class FeatureScanNetworkInformationsConfig implements FeatureConfig {

    final static FeatureType type = FeatureType.FEATURE_SCAN_OS_INFORMATIONS
    boolean failOnError = true

    @Override
    @NonCPS
    FeatureType getType() {
        return type
    }

    @Override
    @NonCPS
    boolean isFailOnError() {
        return failOnError
    }

}
