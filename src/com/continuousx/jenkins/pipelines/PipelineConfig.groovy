package com.continuousx.jenkins.pipelines

import com.continuousx.jenkins.LogLevelType

abstract class PipelineConfig implements Serializable {

    PipelineType type //must be set in implemented PipelineConfiguration and set `final static`
    LogLevelType logLevelType

}
