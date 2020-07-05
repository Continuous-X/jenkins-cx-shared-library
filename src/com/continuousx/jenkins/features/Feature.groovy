package com.continuousx.jenkins.features

interface Feature {

    FeatureConfig config

    void runFeature()
    void publishMetricOperating()

}
