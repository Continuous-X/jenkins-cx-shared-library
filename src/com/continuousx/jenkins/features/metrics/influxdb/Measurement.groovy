package com.continuousx.jenkins.features.metrics.influxdb

interface Measurement extends com.continuousx.jenkins.features.metrics.Measurement {

    Map getDataMap()
    Map getDataMapTags()

}
