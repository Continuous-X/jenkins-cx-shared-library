package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.utils.github.GitURLParser
import com.continuousx.utils.jenkins.JenkinsPluginCheck
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature

abstract class AbstractFeature implements Feature, Serializable{

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    def jenkinsContext
    List<String> neededPlugins = []
    MeasurementOperatingFeature measurementOperating = new MeasurementOperatingFeature()
    FeatureConfig config
    InfluxDBFeature metrics

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractFeature(
            final def jenkinsContext,
            final List<String> neededPlugins,
            final FeatureConfig featureConfig) {
        Objects.requireNonNull(jenkinsContext)
        Objects.requireNonNull(neededPlugins)
        Objects.requireNonNull(featureConfig)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.config = featureConfig

        this.jenkinsContext.log.info("ENV in Feature: ${this.jenkinsContext.env.toString()}")
        measurementOperating.featureType = this.config.type
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

    abstract void runFeatureImpl()

    @Override
    void runFeature() {
        if(checkNeededPlugins()) {
            try {
                final long startTime = System.nanoTime()
                runFeatureImpl()
                final long duration = (long) ((System.nanoTime() - startTime) / 100000)
                measurementOperating.setDuration(duration)
            } catch (final Exception exception) {
                if (this.config.failOnError) {
                    throw exception
                } else {
                    jenkinsContext.log.warning("${this.config.type} failed: ${exception.message}")
                }
            } finally {
                publishMetricOperating()
            }
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
            publishMetricOperating()
        }
    }

    void publishMetricOperating() {
        metrics.publishMetricOperating(measurementOperating)
    }

}
