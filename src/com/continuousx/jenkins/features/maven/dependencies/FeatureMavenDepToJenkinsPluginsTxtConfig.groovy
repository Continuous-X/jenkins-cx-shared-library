package com.continuousx.jenkins.features.maven.dependencies

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType

class FeatureMavenDepToJenkinsPluginsTxtConfig implements FeatureConfig {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_POM_CONVERT_DEP_TO_JENKINS_PLUGINS_TXT
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
