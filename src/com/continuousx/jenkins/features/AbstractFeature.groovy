package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.jenkins.utils.JenkinsPluginCheck
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder

import com.continuousx.jenkins.features.metrics.influxdb.operating.MeasurementOperatingFeature

abstract class AbstractFeature implements Feature, Serializable{

    def jenkinsContext
    List<String> neededPlugins = []
    boolean failOnError
    LogLevelType logLevel = LogLevelType.INFO
    InfluxDBFeature metrics
    Measurement measurement

    @SuppressWarnings('GroovyUntypedAccess')
    AbstractFeature(final def jenkinsContext, final List<String> neededPlugins, final boolean failOnError, final LogLevelType logLevel) {
        Objects.nonNull(jenkinsContext)
        Objects.nonNull(neededPlugins)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.failOnError
        this.logLevel = logLevel

        metrics = new InfluxDBFeatureBuilder(jenkinsContext).build()
        measurement = new MeasurementOperatingFeature()
        measurement.setFailOnError(failOnError)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

    abstract Feature runImpl()

    @Override
    void run() {
        runImpl()
        publishMetric()
    }

    private publishMetric() {
        metrics.publishMetricOperating(measurement)
    }

}
