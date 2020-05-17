package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType


interface StageConfig {
    StageType getStageType()
    boolean isActive()
    boolean isFailOnError()

    LogLevelType logLevelType()
}