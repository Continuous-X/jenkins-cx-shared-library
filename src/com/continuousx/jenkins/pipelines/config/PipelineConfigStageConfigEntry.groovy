package com.continuousx.jenkins.pipelines.config

import com.continuousx.jenkins.stages.StageConfig
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigStageConfigEntry implements Serializable {
    String name
    StageConfig stageConfig
}
