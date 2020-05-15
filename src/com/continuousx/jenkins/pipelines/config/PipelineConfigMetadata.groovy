package com.continuousx.jenkins.pipelines.config

import com.continuousx.jenkins.pipelines.PipelineType
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigMetadata implements Serializable {
    String name
    PipelineType type
}
