package com.continuousx.jenkins.features.metrics.influxdb.operating

import com.continuousx.jenkins.features.metrics.MeasurementType
import com.continuousx.jenkins.features.metrics.influxdb.Measurement

class MeasurementOperating implements Measurement {

    MeasurementType type
    private boolean failOnError
    private String duration

    protected Map dataMap = [:]
    protected Map dataMapTags = [:]

    @Override
    String getType() {
        type
    }

    @Override
    Map getDataMap() {
        dataMap
    }

    @Override
    Map getDataMapTags() {
        dataMapTags
    }
}
