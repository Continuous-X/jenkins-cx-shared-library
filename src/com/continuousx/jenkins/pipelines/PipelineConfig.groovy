package com.continuousx.jenkins.pipelines

import com.continuousx.jenkins.LogLevelType

interface PipelineConfig extends Serializable {

    PipelineType type //must be set in implemented PipelineConfiguration and set `final static`
    LogLevelType logLevelType

}
