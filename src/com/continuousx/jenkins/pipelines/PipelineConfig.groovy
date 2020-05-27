package com.continuousx.jenkins.pipelines

import com.continuousx.jenkins.LogLevelType

interface PipelineConfig extends Serializable {
    PipelineType getType()
    LogLevelType getLogLevelType()
}