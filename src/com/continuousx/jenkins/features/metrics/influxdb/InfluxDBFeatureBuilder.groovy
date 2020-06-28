package com.continuousx.jenkins.features.metrics.influxdb

import com.cloudbees.groovy.cps.NonCPS

class InfluxDBFeatureBuilder {

    private def jenkinsContext

    @SuppressWarnings('GroovyUntypedAccess')
    InfluxDBFeatureBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    InfluxDBFeatureImpl build() {
        new InfluxDBFeatureImpl(jenkinsContext)
    }
}
