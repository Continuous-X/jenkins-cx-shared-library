package com.continuousx.jenkins.features

interface Feature {

    void run()
    FeatureType getType()
    void publishMetricOperating()

}
