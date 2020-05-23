package com.continuousx.jenkins.pipelines.config

import com.continuousx.jenkins.stages.config.StageConfig
import com.continuousx.jenkins.stages.StageType
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigStageConfigEntry implements Serializable {
    StageType name
    StageConfig stageConfig
}
