package com.continuousx.jenkins.features.metrics.influxdb.measurements.result

import com.cloudbees.groovy.cps.NonCPS

enum MeasurementResultType {

    CX_CICD_RESULT_OWAS_DEPENDENCY_CHECK,
    CX_CICD_RESULT_JUNIT

    @Override
    @NonCPS
    String toString() {
        name().toLowerCase()
    }

}
