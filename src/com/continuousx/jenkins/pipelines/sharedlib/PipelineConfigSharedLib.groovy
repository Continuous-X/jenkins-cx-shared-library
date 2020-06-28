package com.continuousx.jenkins.pipelines.sharedlib

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigSharedLib implements PipelineConfig {
    private final static PipelineType pipelineType = PipelineType.PIPELINE_JENKINS_SHARED_LIB
    LogLevelType logLevelType

    @Override
    PipelineType getType() {
        return pipelineType
    }

    @Override
    LogLevelType getLogLevelType() {
        return logLevelType
    }

}
