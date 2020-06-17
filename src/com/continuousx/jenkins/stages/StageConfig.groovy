package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.github.BranchType

interface StageConfig extends Serializable {

    StageType getType()
    boolean isActive()
    boolean isFailOnError()
    BranchType getAllowedBranch()
    LogLevelType logLevelType()
}