package com.continuousx.jenkins.stages.github.protection

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageGithubProtectionCheckConfig implements StageConfig {

    final static StageType type = StageType.STAGE_GITHUB_PROTECTION_CHECK
    LogLevelType logLevelType = LogLevelType.WARNING
    BranchType allowedBranch = BranchType.ALL
    boolean active = true
    boolean failOnError = true

    @Override
    StageType getType() {
        return type
    }

    @Override
    LogLevelType getLogLevelType() {
        return logLevelType
    }

    @Override
    BranchType getAllowedBranch() {
        return allowedBranch
    }

    @Override
    boolean isActive() {
        return active
    }

    @Override
    boolean isFailOnError() {
        return failOnError
    }

}
