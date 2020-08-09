package com.continuousx.jenkins.pipelines.mavenbuild

class PipelineMavenBuildBuilder {

    private def jenkinsContext
    private PipelineMavenBuildConfig pipelineConfig

    @SuppressWarnings('GroovyUntypedAccess')
    PipelineMavenBuildBuilder(final def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    PipelineMavenBuildBuilder withPipelineConfig(final PipelineMavenBuildConfig pipelineConfig) {
        Objects.nonNull(pipelineConfig)
        this.pipelineConfig = pipelineConfig
        this
    }

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    PipelineMavenBuildImpl build() {
        new PipelineMavenBuildImpl(jenkinsContext, pipelineConfig)
    }

}
