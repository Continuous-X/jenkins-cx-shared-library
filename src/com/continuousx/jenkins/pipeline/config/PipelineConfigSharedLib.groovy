package com.continuousx.jenkins.pipeline.config

class PipelineConfigSharedLib implements PipelineConfig {
    final static PipelineType pipelineType = PipelineType.PIPELINE_JENKINS_SHARED_LIB
    LogLevelType logLevelType

    @Override
    LogLevelType logLevelType() {
        return logLevelType
    }
}
