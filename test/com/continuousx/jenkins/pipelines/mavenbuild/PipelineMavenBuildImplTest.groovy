package com.continuousx.jenkins.pipelines.mavenbuild

import com.continuousx.jenkins.pipelines.Pipeline
import resource.jenkins.PipelineMock
import spock.lang.Specification

class PipelineMavenBuildImplTest extends Specification {

    PipelineMock m_pipelineMock

    def setup() {
        m_pipelineMock = Mock(PipelineMock)
    }

    def "should be create instance"() {
        given:
        PipelineMavenBuildBuilder pipelineMavenBuildBuilder = new PipelineMavenBuildBuilder(m_pipelineMock)
                .withPipelineConfig(new PipelineMavenBuildConfig())

        when:
        Pipeline pipeline = pipelineMavenBuildBuilder.build()

        then:
        assert pipeline != null
    }
}
