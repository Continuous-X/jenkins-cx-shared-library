package com.continuousx.jenkins.features.maven.build

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.features.maven.FeatureMavenConfig

class FeatureMavenBuildConfig extends FeatureMavenConfig {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_BUILD
    boolean failOnError = true
    LogLevelType logLevelType = LogLevelType.INFO

}
