package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageJenkinsConvertPluginsTxtConfig implements StageConfig {

    final static StageType type = StageType.STAGE_JENKINS_CONVERT_PLUGINS_TXT
    LogLevelType logLevelType = LogLevelType.WARNING
    BranchType allowedBranch = BranchType.ALL
    boolean active = true
    boolean failOnError = true

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
    LogLevelType getLogLevelType() {
        logLevelType
    }

    @Override
    @SuppressWarnings('UnnecessaryReturnKeyword')
    BranchType getAllowedBranch() {
        allowedBranch
    }

}
