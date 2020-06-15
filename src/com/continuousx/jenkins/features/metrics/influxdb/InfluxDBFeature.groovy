package com.continuousx.jenkins.features.metrics.influxdb

import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperating
import com.continuousx.jenkins.features.metrics.influxdb.measurements.result.MeasurementResult

interface InfluxDBFeature {

    public final static String INFLUX_TARGET_OPERATING = 'cx-operating'
    public final static String INFLUX_TARGET_CICD_RESULTS = 'cx-cicd'

    void publishMetricOperating(MeasurementOperating measurement)
    void publishMetricCicdResult(MeasurementResult measurement)

}
