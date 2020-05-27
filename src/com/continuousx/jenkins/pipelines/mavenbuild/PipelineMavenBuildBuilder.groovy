package com.continuousx.jenkins.pipelines.mavenbuild

class PipelineMavenBuildBuilder {

    private def m_jenkinsContext
    private PipelineMavenBuildConfig m_pipelineConfig

    @SuppressWarnings('GroovyUntypedAccess')
    PipelineMavenBuildBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.m_jenkinsContext = jenkinsContext
    }

    PipelineMavenBuildBuilder withPipelineConfig(final PipelineMavenBuildConfig pipelineConfig) {
        Objects.nonNull(pipelineConfig)
        this.m_pipelineConfig = pipelineConfig
        return this
    }

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    PipelineMavenBuildImpl build() {
        new PipelineMavenBuildImpl(
                jenkinsContext: m_jenkinsContext,
                config: m_pipelineConfig,
                logLevel: m_pipelineConfig.logLevelType
        )
    }

}
