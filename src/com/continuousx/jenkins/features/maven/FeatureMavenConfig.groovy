package com.continuousx.jenkins.features.maven

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType

class FeatureMavenConfig implements FeatureConfig, Serializable {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_BUILD
    boolean failOnError = true
    LogLevelType logLevelType = LogLevelType.INFO

}
