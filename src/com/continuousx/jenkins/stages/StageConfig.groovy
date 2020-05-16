package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.stages.StageType

interface StageConfig {
    StageType getStageType()
    boolean isActive()
    boolean isFailOnError()
    LogLevelType logLevelType()
}