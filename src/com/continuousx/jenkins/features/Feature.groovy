package com.continuousx.jenkins.features

interface Feature {

    void runFeature()
    FeatureType getType()
    void publishMetricOperating()

}
