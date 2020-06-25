package com.continuousx.jenkins.stages.maven.install

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.github.BranchType
import com.continuousx.jenkins.stages.StageType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.utils.github.BranchType

class StageMavenCompileConfig implements StageConfig, Serializable {

    final static StageType type = StageType.STAGE_MAVEN_COMPILE
    boolean active = true
    boolean failOnError = true
    BranchType allowedBranch = BranchType.ALL
    LogLevelType logLevelType

    @Override
    StageType getType() {
        type
    }

    @Override
    boolean isActive() {
        active
    }

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
