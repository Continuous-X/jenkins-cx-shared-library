package com.continuousx.jenkins.pipeline.config

import groovy.transform.TypeChecked

@TypeChecked
class PipelineMetadata implements Serializable {
    String name
    PipelineType type
}
