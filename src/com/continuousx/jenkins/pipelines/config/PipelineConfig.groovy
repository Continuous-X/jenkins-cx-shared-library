package com.continuousx.jenkins.pipelines.config

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineType

interface PipelineConfig extends Serializable {
    PipelineType getPipelineType()
    LogLevelType getLogLevelType()
}