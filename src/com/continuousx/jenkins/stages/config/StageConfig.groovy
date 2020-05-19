package com.continuousx.jenkins.stages.config

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.stages.StageType


interface StageConfig extends Serializable {
    StageType getStageType()
    boolean isActive()
    boolean isFailOnError()

    LogLevelType logLevelType()
}