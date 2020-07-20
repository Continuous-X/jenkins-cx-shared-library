package com.continuousx.jenkins.features.metrics.influxdb

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType

class InfluxDBFeatureBuilder {

    private def jenkinsContext
    private LogLevelType logLevelType

    @SuppressWarnings('GroovyUntypedAccess')
    InfluxDBFeatureBuilder(final def jenkinsContext, LogLevelType logLevelType1 = LogLevelType.WARNING) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logLevelType = logLevelType1
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    InfluxDBFeatureImpl build() {
        new InfluxDBFeatureImpl(jenkinsContext, logLevelType)
    }
}
