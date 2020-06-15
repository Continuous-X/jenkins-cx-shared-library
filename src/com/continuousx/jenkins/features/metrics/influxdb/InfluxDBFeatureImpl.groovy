package com.continuousx.jenkins.features.metrics.influxdb

import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.features.github.GitURLParser
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature

class InfluxDBFeatureImpl implements InfluxDBFeature {

    def jenkinsContext
    List<String> neededPlugins = []
    final static FeatureType type = FeatureType.FEATURE_METRIC_INFLUXDB
    MeasurementOperatingFeature measurementOperating = new MeasurementOperatingFeature()

    @SuppressWarnings('GroovyUntypedAccess')
    protected InfluxDBFeatureImpl(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = ['influxdb']
        measurementOperating.featureType = type
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurementOperating.setGHOrganization(gitUrlParser.getOrgaName())
            measurementOperating.setGHRepository(gitUrlParser.getRepoName())
        }
    }

    @Override
    void publishMetricOperating(Measurement measurement) {
/*
        def dataMap = [:]
        def dataMapTags = [:]
        measurement.getDataMap().each { key, value ->
            dataMap[key] = value
        }
        measurement.getDataMapTags().each { key, value ->
            dataMapTags[key] = value
        }
*/
        jenkinsContext.influxDbPublisher(selectedTarget: INFLUXDB_TARGET_CX_OPERATING, measurementName: measurement.getType(), customDataMap: measurement.getDataMap(), customDataMapTags: measurement.getDataMapTags() )
    }

    @Override
    void publishMetricCicdResult(Measurement measurement) {
        jenkinsContext.influxDbPublisher(selectedTarget: INFLUXDB_TARGET_CX_CICD, measurementName: measurement.getType(), customDataMap: measurement.getDataMap(), customDataMapTags: measurement.getDataMapTags() )
    }

}
