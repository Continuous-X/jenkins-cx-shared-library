package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.utils.github.BranchType

interface StageConfig extends Serializable {

    public StageType type //must be set in implemented StageConfiguration and set `final static`
    public LogLevelType logLevelType = LogLevelType.INFO
    public boolean active = true
    public boolean failOnError = true
    public BranchType allowedBranch = BranchType.ALL

}
