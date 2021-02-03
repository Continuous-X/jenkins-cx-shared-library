package com.continuousx.jenkins.stages

import com.continuousx.utils.github.BranchType

interface StageConfig extends Serializable {

    StageType getType()
    boolean isActive()
    boolean isFailOnError()
    BranchType getAllowedBranch()

}
