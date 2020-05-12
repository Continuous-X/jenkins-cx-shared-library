package com.continuousx.jenkins.pipeline.config

import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfig implements Serializable {
    PipelineMetadata metadata
    LogLevelType logLevel
}
