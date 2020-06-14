package com.continuousx.jenkins.features.metrics.influxdb


import resource.jenkins.PipelineMock
import spock.lang.Specification

class InfluxDBBuilderTest extends Specification {

    PipelineMock pipelineMock

    def setup() {
        pipelineMock = Mock(PipelineMock)
    }

    def "should be create instance"() {
        given:
        final InfluxDBBuilder influxDBBuilder = new InfluxDBBuilder(pipelineMock)

        when:
        final Metric metric = influxDBBuilder.build()

        then:
        assert metric != null
        assert metric instanceof InfluxDBImpl
    }

}
