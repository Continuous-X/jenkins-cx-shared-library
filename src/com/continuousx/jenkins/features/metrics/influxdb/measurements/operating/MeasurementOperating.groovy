package com.continuousx.jenkins.features.metrics.influxdb.measurements.operating

interface MeasurementOperating {

    Map getDataMap()
    Map getDataMapTags()
    MeasurementOperatingType getType()

}
