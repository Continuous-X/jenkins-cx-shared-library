package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType

interface StageConfig extends Serializable {

    StageType getType()
    boolean isActive()
    boolean isFailOnError()

    LogLevelType logLevelType()
}