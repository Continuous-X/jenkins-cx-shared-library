package com.continuousx.jenkins.features.maven.build

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.features.maven.FeatureMavenConfig

class FeatureMavenBuildConfig extends FeatureMavenConfig {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_BUILD
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
