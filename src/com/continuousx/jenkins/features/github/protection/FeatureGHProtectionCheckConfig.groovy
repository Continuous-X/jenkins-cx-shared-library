package com.continuousx.jenkins.features.github.protection

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType

class FeatureGHProtectionCheckConfig implements FeatureConfig {

    final static FeatureType type = FeatureType.FEATURE_GH_PROTECTION_CHECK
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
