package com.continuousx.jenkins.pipelines.sharedlib

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import groovy.transform.TypeChecked

@TypeChecked
class PipelineJenkinsSharedLibConfig implements PipelineConfig {

    final static PipelineType type = PipelineType.PIPELINE_JENKINS_SHARED_LIB
    LogLevelType logLevelType = LogLevelType.WARNING

    @Override
    PipelineType getType() {
        return type
    }

    @Override
    LogLevelType getLogLevelType() {
        return logLevelType
    }
}
