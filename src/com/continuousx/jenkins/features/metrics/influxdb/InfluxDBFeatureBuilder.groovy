package com.continuousx.jenkins.features.metrics.influxdb

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class InfluxDBFeatureBuilder {

    private def jenkinsContext
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    InfluxDBFeatureBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    InfluxDBFeatureBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    InfluxDBFeatureImpl build() {
        new InfluxDBFeatureImpl(jenkinsContext, logger)
    }
}
