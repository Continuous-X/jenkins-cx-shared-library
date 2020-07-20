package com.continuousx.jenkins.pipelines

import com.continuousx.jenkins.logger.LogLevelType

interface PipelineConfig extends Serializable {

    PipelineType getType()
    LogLevelType getLogLevelType()

}
