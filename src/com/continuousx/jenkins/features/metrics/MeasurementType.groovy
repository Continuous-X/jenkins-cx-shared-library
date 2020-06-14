package com.continuousx.jenkins.features.metrics

enum MeasurementType {

    MEASUREMENT_CX_OPERATING_FEATURE,
    MEASUREMENT_CX_OPERATING_STAGE,
    MEASUREMENT_CX_OPERATING_PIPELINE,
    MEASUREMENT_CX_CICD

    String toString() {
        name().toUpperCase()
    }

}
