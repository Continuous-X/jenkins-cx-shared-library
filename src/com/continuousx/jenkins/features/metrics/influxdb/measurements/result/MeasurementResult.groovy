package com.continuousx.jenkins.features.metrics.influxdb.measurements.result

interface MeasurementResult {

    Map getDataMap()
    Map getDataMapTags()

    MeasurementResultType getType()

}
