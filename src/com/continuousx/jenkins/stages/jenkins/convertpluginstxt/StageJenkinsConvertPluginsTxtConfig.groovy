package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.github.BranchType
import com.continuousx.jenkins.stages.StageType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.utils.github.BranchType

class StageJenkinsConvertPluginsTxtConfig implements StageConfig, Serializable {

    final static StageType type = StageType.STAGE_JENKINS_CONVERT_PLUGINS_TXT
    boolean active = true
    boolean failOnError = true
    BranchType allowedBranch = BranchType.ALL
    LogLevelType logLevelType

    @Override
    @NonCPS
    StageType getType() {
        type
    }

    @Override
    @NonCPS
    @SuppressWarnings('ClashingGetters')
    boolean isActive() {
        active
    }

    @Override
    @SuppressWarnings('ClashingGetters')
    boolean isFailOnError() {
        failOnError
    }

    @Override
    LogLevelType logLevelType() {
        logLevelType
    }

    @Override
    @SuppressWarnings('UnnecessaryReturnKeyword')
    BranchType getAllowedBranch() {
        allowedBranch
    }

}
