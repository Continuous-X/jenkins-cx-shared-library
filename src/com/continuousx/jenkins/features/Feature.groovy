package com.continuousx.jenkins.features

interface Feature {

    List<String> neededPlugins
    FeatureConfig featureConfig

    void runFeature()
    void publishMetricOperating()

}
