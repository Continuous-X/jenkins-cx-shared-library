package com.continuousx.jenkins.features.jenkins.globallib

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.logger.LogLevelType

class FeatureJenkinsGlobalLibVersionConfig implements FeatureConfig {

    final static FeatureType type = FeatureType.FEATURE_JENKINS_GLOBAL_LIB_VERISON
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
