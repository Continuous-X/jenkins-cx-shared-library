package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageJenkinsConvertPluginsTxtConfig implements StageConfig {

    final static StageType type = StageType.STAGE_JENKINS_CONVERT_PLUGINS_TXT
    BranchType allowedBranch = BranchType.ALL
    boolean active = true
    boolean failOnError = true

    @Override
    @NonCPS
    StageType getType() {
        type
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
    BranchType getAllowedBranch() {
        allowedBranch
    }

}
