package com.continuousx.jenkins.pipeline.config

interface PipelineConfig {
    PipelineType getPipelineType()
    LogLevelType logLevelType()
}