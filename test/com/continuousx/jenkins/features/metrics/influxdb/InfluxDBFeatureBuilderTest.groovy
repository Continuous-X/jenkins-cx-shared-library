package com.continuousx.jenkins.features.metrics.influxdb

import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperating
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipeline
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipelineStage
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.StageType
import resource.jenkins.PipelineMock
import spock.lang.Specification

class InfluxDBFeatureBuilderTest extends Specification {

    PipelineMock pipelineMock
    private InfluxDBFeatureImpl influxFeature

    def setup() {
        pipelineMock = Mock(PipelineMock)
        influxFeature = new InfluxDBFeatureBuilder(pipelineMock).build()
    }

    @SuppressWarnings('JUnitPublicNonTestMethod')
    void cleanup() {
        influxFeature = null
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

    void "should set measurement 4 features"() {
        given:
        MeasurementOperating measurement = new MeasurementOperatingFeature()
        measurement.setDuration(1)
        measurement.setFeatureType(FeatureType.FEATURE_METRIC_INFLUXDB)
        measurement.setGHOrganization('gh-orga')
        measurement.setGHRepository('gh-repo')

        MeasurementOperating measurement2 = new MeasurementOperatingFeature()
        measurement2.setDuration(3)
        measurement2.setFeatureType(FeatureType.FEATURE_METRIC_INFLUXDB)

        influxFeature.addFeatureMeasurement(measurement2)
        influxFeature.addFeatureMeasurement(measurement)

        when:
        int result = influxFeature.featureList.dataMap.size()
        println "measurements datamap: ${influxFeature.featureList.dataMap}"
        println "measurements datamaptags: ${influxFeature.featureList.dataMapTags}"

        then:
        result == 2
    }

    void "should set measurement 4 pipelines"() {
        given:
        MeasurementOperating measurement = new MeasurementOperatingPipeline()
        measurement.setPipelineType(PipelineType.PIPELINE_MAVEN_BUILD)

        MeasurementOperating measurement2 = new MeasurementOperatingPipeline()
        measurement2.setPipelineType(PipelineType.PIPELINE_MAVEN_BUILD)

        influxFeature.addPipelineMeasurement(measurement2)
        influxFeature.addPipelineMeasurement(measurement)

        when:
        int result = influxFeature.pipelineList.dataMap.size()
        println "measurements datamap: ${influxFeature.pipelineList.dataMap}"
        println "measurements datamaptags: ${influxFeature.pipelineList.dataMapTags}"

        then:
        result == 2
    }

    void "should set measurement 4 stages"() {
        given:
        MeasurementOperating measurement = new MeasurementOperatingPipelineStage()
        measurement.setStageType(StageType.STAGE_MAVEN_INSTALL)
        measurement.setDuration(1)
        measurement.setActive(true)
        measurement.setFailOnError(false)

        MeasurementOperating measurement2 = new MeasurementOperatingPipelineStage()
        measurement2.setStageType(StageType.STAGE_MAVEN_INSTALL)
        measurement2.setDuration(10)
        measurement2.setActive(true)
        measurement2.setFailOnError(true)

        influxFeature.addPipelineStageMeasurement(measurement2)
        influxFeature.addPipelineStageMeasurement(measurement)

        when:
        int result = influxFeature.stageList.dataMap.size()
        println "measurements datamap: ${influxFeature.stageList.dataMap}"
        println "measurements datamaptags: ${influxFeature.stageList.dataMapTags}"

        then:
        result == 2
    }

}
