package com.continuousx.jenkins.features.maven.build.wrapper

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.features.maven.FeatureMavenConfig

class FeatureMavenWrapperBuildConfig extends FeatureMavenConfig {

    final static FeatureType type = FeatureType.FEATURE_MAVEN_WRAPPER_BUILD
    boolean failOnError = true

    @Override
    @NonCPS
    FeatureType getType() {
        return type
    }

    @Override
    @NonCPS
    boolean isFailOnError() {
        return failOnError
    }

}
