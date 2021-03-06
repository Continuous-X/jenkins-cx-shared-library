package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS

enum FeatureType {

    FEATURE_METRIC_INFLUXDB,
    FEATURE_MAVEN_POM_CONVERT_DEP_TO_JENKINS_PLUGINS_TXT,
    FEATURE_MAVEN_BUILD,
    FEATURE_MAVEN_WRAPPER_BUILD,
    FEATURE_GH_PROTECTION_CHECK,
    FEATURE_JENKINS_GLOBAL_LIB_VERISON,
    FEATURE_SCAN_OS_INFORMATIONS,
    FEATURE_SCAN_NETWORK_INFORMATIONS

    @Override
    @NonCPS
    String toString() {
        name().toLowerCase()
    }

}
