package com.continuousx.jenkins.features.metrics.influxdb

class InfluxDBFeatureBuilder {

    def jenkinsContext

    @SuppressWarnings('GroovyUntypedAccess')
    InfluxDBFeatureBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    @SuppressWarnings('GroovyUntypedAccess')
    InfluxDBFeatureImpl build() {
        new InfluxDBFeatureImpl(jenkinsContext)
    }
}
