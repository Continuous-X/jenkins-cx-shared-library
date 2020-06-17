package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.github.GitURLParser
import com.continuousx.jenkins.features.jenkins.utils.JenkinsPluginCheck
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature

abstract class AbstractFeature implements Feature, Serializable{

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    def jenkinsContext
    List<String> neededPlugins = []
    MeasurementOperatingFeature measurementOperating = new MeasurementOperatingFeature()
    FeatureType type
    InfluxDBFeature metrics
    boolean failOnError

    LogLevelType logLevel = LogLevelType.INFO

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractFeature(
            final def jenkinsContext,
            final List<String> neededPlugins,
            final boolean failOnError,
            final FeatureType type,
            LogLevelType logLevel = LogLevelType.WARNING) {
        Objects.requireNonNull(jenkinsContext)
        Objects.requireNonNull(neededPlugins)
        Objects.requireNonNull(type)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.failOnError = failOnError
        this.type = type
        this.logLevel = logLevel

        measurementOperating.featureType = this.type
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurementOperating.setGHOrganization(gitUrlParser.getOrgaName())
            measurementOperating.setGHRepository(gitUrlParser.getRepoName())
        }
        metrics = new InfluxDBFeatureBuilder(jenkinsContext).build()
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

    abstract Feature runFeatureImpl()

    @Override
    void runFeature() {
        try {
            final long startTime = System.nanoTime()
            runFeatureImpl()
            final long duration = (long) ((System.nanoTime() - startTime) / 100000)
            measurementOperating.setDuration(duration)
        } catch (final Exception exception) {
            if (failOnError) {
                throw exception
            } else {
                jenkinsContext.log.warning("${type} failed: ${exception.message}")
            }
        } finally {
            publishMetricOperating()
        }
    }

    void publishMetricOperating() {
        metrics.publishMetricOperating(measurementOperating)
    }

}
