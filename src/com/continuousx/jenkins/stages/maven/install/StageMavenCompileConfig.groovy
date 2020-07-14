package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageMavenCompileConfig implements StageConfig {

    final static StageType type = StageType.STAGE_MAVEN_COMPILE
    LogLevelType logLevelType = LogLevelType.WARNING
    final static BranchType allowedBranch = BranchType.ALL
    final static boolean active = true
    final static boolean failOnError = true

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
