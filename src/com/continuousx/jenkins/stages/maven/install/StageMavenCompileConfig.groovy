package com.continuousx.jenkins.stages.maven.install

import com.cloudbees.groovy.cps.NonCPS
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

    @Override
    @NonCPS
    boolean isActive() {
        return active
    }

    @Override
    @NonCPS
    boolean isFailOnError() {
        return failOnError
    }

}
