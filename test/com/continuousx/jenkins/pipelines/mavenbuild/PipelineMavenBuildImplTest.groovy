package com.continuousx.jenkins.pipelines.mavenbuild

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.pipelines.Pipeline
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import resource.jenkins.PipelineMock
import spock.lang.Specification
import spock.lang.Unroll

class PipelineMavenBuildImplTest extends Specification {

    PipelineMock pipelineMock
    PipelineMavenBuildBuilder featureBuilder

    def setup() {
        pipelineMock = Mock(PipelineMock)
        featureBuilder = new PipelineMavenBuildBuilder(pipelineMock)
    }

    def "should be create instance"() {
        given:
        Pipeline pipeline = featureBuilder.withPipelineConfig(new PipelineMavenBuildConfig()).build()

        when:
        PipelineConfig config = pipeline.getPipelineConfig()

        then:
        assert pipeline != null
        assert config.getType() == PipelineType.PIPELINE_MAVEN_BUILD
    }

    @Unroll
    def "should be config Pipeline Object"() {
        expect:
        Pipeline pipeline = featureBuilder
                .withPipelineConfig(
                        new PipelineMavenBuildConfig(
                                logLevelType: loglevel
                        )
                )
                .build()

        assert pipeline != null

        assert pipeline.pipelineConfig.logLevelType == expectedLogLevel

        where:
        loglevel             || expectedLogLevel
        LogLevelType.DEBUG   || LogLevelType.DEBUG
        LogLevelType.INFO    || LogLevelType.INFO
        LogLevelType.WARNING || LogLevelType.WARNING
        LogLevelType.ERROR   || LogLevelType.ERROR
    }

    def 'should be config Feature Object failed'() {
        when:
        Pipeline pipeline = featureBuilder
                .withPipelineConfig(
                        new PipelineMavenBuildConfig(
                                logLevelType: loglevel
                        )
                )
                .build()

        assert pipeline != null

        then:
        thrown(expectedException)

        where:
        loglevel || expectedException
        null     || NullPointerException
    }

}
