package com.continuousx.jenkins.features.scan.os

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.logger.LogLevelType

class FeatureScanOsInformationsConfig implements FeatureConfig {

    final static FeatureType type = FeatureType.FEATURE_SCAN_OS_INFORMATIONS
    boolean failOnError = true
    LogLevelType logLevelType = LogLevelType.INFO

    @Override
    @NonCPS
    FeatureType getType() {
        return type
    }

    @Override
    @NonCPS
    LogLevelType getLogLevelType() {
        return logLevelType
    }

    @Override
    @NonCPS
    boolean isFailOnError() {
        return failOnError
    }

}
