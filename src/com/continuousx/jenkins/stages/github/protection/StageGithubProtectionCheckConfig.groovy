package com.continuousx.jenkins.stages.github.protection

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageGithubProtectionCheckConfig implements StageConfig {

    final static StageType type = StageType.STAGE_GITHUB_PROTECTION_CHECK
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
