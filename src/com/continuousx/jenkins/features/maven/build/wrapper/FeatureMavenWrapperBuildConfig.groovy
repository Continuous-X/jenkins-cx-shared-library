package com.continuousx.jenkins.features.maven.build.wrapper

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.features.maven.FeatureMavenConfig

class FeatureMavenWrapperBuildConfig extends FeatureMavenConfig {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_WRAPPER_BUILD
    boolean failOnError = true
    LogLevelType logLevelType = LogLevelType.INFO

}
