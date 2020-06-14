package com.continuousx.jenkins.features.metrics.influxdb

class InfluxDBBuilder {

    def jenkinsContext

    @SuppressWarnings('GroovyUntypedAccess')
    InfluxDBBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    @SuppressWarnings('GroovyUntypedAccess')
    InfluxDBImpl build() {
        new InfluxDBImpl(jenkinsContext)
    }
}
