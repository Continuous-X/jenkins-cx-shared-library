package com.continuousx.jenkins.pipeline.config

class PipelineConfigMavenBuild implements PipelineConfig {
    private final static PipelineType pipelineType = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType

    @Override
    PipelineType getPipelineType() {
        return pipelineType
    }

    @Override
    LogLevelType logLevelType() {
        return logLevelType
    }
}
