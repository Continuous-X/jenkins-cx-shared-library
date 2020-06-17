package com.continuousx.jenkins.features.metrics.influxdb

import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperating
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipeline
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipelineStage
import com.continuousx.jenkins.features.metrics.influxdb.measurements.result.MeasurementResult

interface InfluxDBFeature {

    public final static String INFLUX_TARGET_OPERATING = 'cx-operating'
    public final static String INFLUX_TARGET_CICD_RESULTS = 'cx-cicd'

    void addPipelineMeasurement(MeasurementOperatingPipeline measurePipeline)
    void addPipelineStageMeasurement(MeasurementOperatingPipelineStage measureStage)
    void addFeatureMeasurement(MeasurementOperatingFeature measureFeature)

    void publishMeasureListFeature()
    void publishMeasureListPipeline()
    void publishMeasureListPipelineStage()

    void publishMetricOperating(MeasurementOperating measurement)
    void publishMetricCicdResult(MeasurementResult measurement)
    void publishJenkinsData()

}
