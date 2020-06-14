package com.continuousx.jenkins.features.metrics.influxdb

class InfluxDBImpl implements InfluxDB {

    final static String INFLUXDB_TARGET_CX_OPERATING = 'cx-operating'
    final static String INFLUXDB_TARGET_CX_CICD = 'cx-cicd'

    def jenkinsContext

    @SuppressWarnings('GroovyUntypedAccess')
    protected InfluxDBImpl(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
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
    void publishMetricCicd(Measurement measurement) {
        jenkinsContext.influxDbPublisher(selectedTarget: INFLUXDB_TARGET_CX_CICD, measurementName: measurement.getType(), customDataMap: measurement.getDataMap(), customDataMapTags: measurement.getDataMapTags() )
    }

}
