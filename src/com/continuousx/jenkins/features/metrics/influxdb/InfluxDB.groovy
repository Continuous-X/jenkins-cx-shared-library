package com.continuousx.jenkins.features.metrics.influxdb

interface InfluxDB {

    void publishMetricOperating(Measurement measurement)
    void publishMetricCicd(Measurement measurement)

}
