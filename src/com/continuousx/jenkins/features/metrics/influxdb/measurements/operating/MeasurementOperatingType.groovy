package com.continuousx.jenkins.features.metrics.influxdb.measurements.operating

import com.cloudbees.groovy.cps.NonCPS

enum MeasurementOperatingType {

    CX_OPERATING_PIPELINES,
    CX_OPERATING_STAGES,
    CX_OPERATING_FEATURES

    @Override
    @NonCPS
    @SuppressWarnings('UnnecessaryReturnKeyword')
    String toString() {
        return name().toLowerCase()
    }

}
