package com.continuousx.jenkins.stages.maven.install


import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageMavenCompileConfig implements StageConfig, Serializable {

    final static StageType TYPE = StageType.STAGE_MAVEN_COMPILE
    boolean active = true
    boolean failOnError = true
    BranchType allowedBranch = BranchType.ALL
    LogLevelType logLevelType

    @Override
    StageType getType() {
        TYPE
    }

    @SuppressWarnings('ClashingGetters')
    @Override
    boolean isActive() {
        active
    }

    @SuppressWarnings('ClashingGetters')
    @Override
    boolean isFailOnError() {
        failOnError
    }

    @Override
    LogLevelType logLevelType() {
        logLevelType
    }

    @Override
    BranchType getAllowedBranch() {
        allowedBranch
    }

}
