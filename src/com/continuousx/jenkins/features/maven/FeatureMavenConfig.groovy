package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType

class FeatureMavenConfig implements FeatureConfig, Serializable {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_BUILD
    boolean failOnError = true
    LogLevelType logLevelType = LogLevelType.INFO

    @Override
    @NonCPS
    FeatureType getType() {
        type
    }

    @Override
    @NonCPS
    boolean isFailOnError() {
        failOnError
    }

    @Override
    @NonCPS
    LogLevelType getLogLevel() {
        logLevelType
    }

}
