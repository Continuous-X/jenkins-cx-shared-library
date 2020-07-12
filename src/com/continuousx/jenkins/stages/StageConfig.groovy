package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.utils.github.BranchType

interface StageConfig extends Serializable {

    StageType type //must be set in implemented StageConfiguration and set `final static`
    LogLevelType logLevelType = LogLevelType.INFO
    boolean active = true
    boolean failOnError = true
    BranchType allowedBranch = BranchType.ALL

}
