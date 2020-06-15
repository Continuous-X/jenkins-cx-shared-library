package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS

enum FeatureType {

    FEATURE_METRIC_INFLUXDB

    @Override
    @NonCPS
    @SuppressWarnings('UnnecessaryReturnKeyword')
    String toString() {
        name().toLowerCase()
    }

}
