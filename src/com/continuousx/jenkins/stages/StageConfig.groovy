package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType


interface StageConfig extends Serializable {
    StageType getStageType()
    boolean isActive()
    boolean isFailOnError()

    LogLevelType logLevelType()
}