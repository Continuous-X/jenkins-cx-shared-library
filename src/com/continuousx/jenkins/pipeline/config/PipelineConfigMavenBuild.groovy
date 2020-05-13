package com.continuousx.jenkins.pipeline.config

class PipelineConfigMavenBuild implements PipelineConfig {
    final static PipelineType pipelineType = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType

    @Override
    LogLevelType logLevelType() {
        return logLevelType
    }
}
