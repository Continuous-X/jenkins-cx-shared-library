package com.continuousx.jenkins.pipeline.config

import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigTmp implements Serializable {
    PipelineMetadata metadata
    LogLevelType logLevel
}
