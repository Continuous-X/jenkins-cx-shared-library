package com.continuousx.jenkins.stages.scanner

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageScanHostConfig implements StageConfig {

    final static StageType type = StageType.STAGE_SCAN_HOST
    LogLevelType logLevelType = LogLevelType.WARNING
    BranchType allowedBranch = BranchType.ALL
    boolean active = true
    boolean failOnError = true

    @Override
    @NonCPS
    StageType getType() {
        return type
    }

    @Override
    @NonCPS
    LogLevelType getLogLevelType() {
        return logLevelType
    }

    @Override
    @NonCPS
    BranchType getAllowedBranch() {
        return allowedBranch
    }

    @SuppressWarnings('ClashingGetters')
    @Override
    @NonCPS
    boolean isActive() {
        return active
    }

    @SuppressWarnings('ClashingGetters')
    @Override
    @NonCPS
    boolean isFailOnError() {
        return failOnError
    }

}
