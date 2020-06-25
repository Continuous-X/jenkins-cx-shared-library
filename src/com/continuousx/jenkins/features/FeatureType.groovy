package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS

enum FeatureType {

    FEATURE_METRIC_INFLUXDB,
    FEATURE_MAVEN_POM_CONVERT_DEP_TO_JENKINS_PLUGINS_TXT

    @Override
    @NonCPS
    String toString() {
        name().toLowerCase()
    }

}
