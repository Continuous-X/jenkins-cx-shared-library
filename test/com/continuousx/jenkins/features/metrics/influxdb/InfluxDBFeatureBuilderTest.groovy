package com.continuousx.jenkins.features.metrics.influxdb


import resource.jenkins.PipelineMock
import spock.lang.Specification

class InfluxDBFeatureBuilderTest extends Specification {

    PipelineMock pipelineMock

    def setup() {
        pipelineMock = Mock(PipelineMock)
    }

    def "should be create instance"() {
        given:
        final InfluxDBFeatureBuilder influxDBBuilder = new InfluxDBFeatureBuilder(pipelineMock)

        when:
        final InfluxDBFeature metric = influxDBBuilder.build()

        then:
        assert metric != null
        assert metric instanceof InfluxDBFeatureImpl
    }

}
