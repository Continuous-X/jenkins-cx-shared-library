package com.continuousx.jenkins.stages.maven.install

import com.cloudbees.groovy.cps.NonCPS
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
    @NonCPS
    StageType getType() {
        TYPE
    }

    @SuppressWarnings('ClashingGetters')
    @Override
    @NonCPS
    boolean isActive() {
        active
    }

    @SuppressWarnings('ClashingGetters')
    @Override
    @NonCPS
    boolean isFailOnError() {
        failOnError
    }

    @Override
    @NonCPS
    LogLevelType logLevelType() {
        logLevelType
    }

    @Override
    @NonCPS
    BranchType getAllowedBranch() {
        allowedBranch
    }

}
