package com.continuousx.jenkins.features.jenkins.globallib

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType

class FeatureJenkinsGlobalLibVersionConfig implements FeatureConfig {

    final static FeatureType type = FeatureType.FEATURE_JENKINS_GLOBAL_LIB_VERISON
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
