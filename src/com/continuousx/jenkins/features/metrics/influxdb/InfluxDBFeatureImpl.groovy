package com.continuousx.jenkins.features.metrics.influxdb

import com.continuousx.jenkins.features.Feature
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.utils.github.GitURLParser
import com.continuousx.utils.jenkins.JenkinsPluginCheck
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperating
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipeline
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipelineStage
import com.continuousx.jenkins.features.metrics.influxdb.measurements.result.MeasurementResult

class InfluxDBFeatureImpl implements InfluxDBFeature, Feature, Serializable {

    def jenkinsContext
    List<String> neededPlugins = []
    final static FeatureType TYPE = FeatureType.FEATURE_METRIC_INFLUXDB
    MeasurementOperatingFeature measurementOperating = new MeasurementOperatingFeature()

    protected List<MeasurementOperatingFeature> featureList = []
    protected List<MeasurementOperatingPipelineStage> stageList = []
    protected List<MeasurementOperatingPipeline> pipelineList = []

    @SuppressWarnings('GroovyUntypedAccess')
    protected InfluxDBFeatureImpl(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = ['influxdb']
        measurementOperating.featureType = TYPE
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurementOperating.setGHOrganization(gitUrlParser.getOrgaName())
            measurementOperating.setGHRepository(gitUrlParser.getRepoName())
        }
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void runFeature() {
        if (checkNeededPlugins()) {
            final long startTime = System.nanoTime()
            publishMeasureListFeature()
            publishMeasureListPipelineStage()
            publishMeasureListPipeline()
            publishJenkinsData()
            final long duration = (long) ((System.nanoTime() - startTime) / 100000)
            measurementOperating.duration = duration
            publishMetricOperating()
        } else {
            jenkinsContext.log.warning("needed plugins not exist: ${neededPlugins}")
        }
    }

    @Override
    void addPipelineMeasurement(final MeasurementOperatingPipeline measurePipeline) {
        Objects.requireNonNull(pipelineList)
        pipelineList.add(measurePipeline)
    }

    @Override
    void addPipelineStageMeasurement(final MeasurementOperatingPipelineStage measureStage) {
        Objects.requireNonNull(stageList)
        stageList.add(measureStage)
    }

    @Override
    void addFeatureMeasurement(final MeasurementOperatingFeature measureFeature) {
        Objects.requireNonNull(measureFeature)
        featureList.add(measureFeature)
    }

    @Override
    void publishMeasureListFeature() {
        publishOperatingMeasurementList(featureList)
    }

    @Override
    void publishMeasureListPipeline() {
        publishOperatingMeasurementList(pipelineList)
    }

    @Override
    void publishMeasureListPipelineStage() {
        publishOperatingMeasurementList(stageList)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    private boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

    @Override
    @SuppressWarnings('GroovyUntypedAccess')
    void publishJenkinsData() {
        if (checkNeededPlugins()) {
            jenkinsContext.influxDbPublisher(selectedTarget:INFLUX_TARGET_OPERATING)
        } else {
            jenkinsContext.log.warning("needed plugins not exist: ${neededPlugins}")
        }
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void publishOperatingMeasurement(final MeasurementOperating measurement) {
        Objects.requireNonNull(measurement)
        if (checkNeededPlugins()) {
            final Map entryDataMap = ["${measurement.getType()}": measurement.getDataMap()]
            final Map entryDataMapTags = ["${measurement.getType()}": measurement.getDataMapTags()]
            publish(INFLUX_TARGET_OPERATING, entryDataMap, entryDataMapTags)
        } else {
            jenkinsContext.log.warning("needed plugins not exist: ${neededPlugins}")
        }
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void publishResultMeasurement(final MeasurementResult measurement) {
        Objects.requireNonNull(measurement)
        if (checkNeededPlugins()) {
            final Map entryDataMap = ["${measurement.getType()}": measurement.getDataMap()]
            final Map entryDataMapTags = ["${measurement.getType()}": measurement.getDataMapTags()]
            publish(INFLUX_TARGET_CICD_RESULTS, entryDataMap, entryDataMapTags)
        } else {
            jenkinsContext.log.warning("needed plugins not exist: ${neededPlugins}")
        }
    }

    private void publishOperatingMeasurementList(final List<MeasurementOperating> measurements) {
        Objects.requireNonNull(measurements)
        measurements.each { measurement ->
            publishOperatingMeasurement(measurement)
        }
    }

    @SuppressWarnings('GroovyUntypedAccess')
    private void publish(final String target, final Map dataMap, final Map dataMapTags) {
        jenkinsContext.influxDbPublisher(selectedTarget:target, customDataMap:dataMap, customDataMapTags:dataMapTags)
    }

    @Override
    void publishMetricOperating(final MeasurementOperating measurement) {
        publishOperatingMeasurement(measurement)
    }

    @Override
    void publishMetricCicdResult(final MeasurementResult measurement) {
        publishResultMeasurement(measurement)
    }

    @Override
    void publishMetricOperating() {
        publishMetricOperating(measurementOperating)
    }

}
