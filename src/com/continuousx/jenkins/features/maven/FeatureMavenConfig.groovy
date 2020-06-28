package com.continuousx.jenkins.features.maven

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType

class FeatureMavenConfig implements FeatureConfig, Serializable {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_BUILD
    boolean failOnError = true
    LogLevelType logLevelType = LogLevelType.INFO

    @Override
    FeatureType getType() {
        type
    }

    @Override
    boolean isFailOnError() {
        failOnError
    }

    @Override
    LogLevelType getLogLevel() {
        logLevelType
    }

}
