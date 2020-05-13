package com.continuousx.jenkins.pipeline.config

class PipelineConfigSharedLib implements PipelineConfig {
    private final static PipelineType pipelineType = PipelineType.PIPELINE_JENKINS_SHARED_LIB
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
