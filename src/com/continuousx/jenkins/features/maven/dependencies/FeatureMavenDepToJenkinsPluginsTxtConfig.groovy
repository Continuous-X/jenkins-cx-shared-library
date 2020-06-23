package com.continuousx.jenkins.features.maven.dependencies

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType

class FeatureMavenDepToJenkinsPluginsTxtConfig implements FeatureConfig, Serializable {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_POM_CONVERT_DEP_TO_JENKINS_PLUGINS_TXT
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
