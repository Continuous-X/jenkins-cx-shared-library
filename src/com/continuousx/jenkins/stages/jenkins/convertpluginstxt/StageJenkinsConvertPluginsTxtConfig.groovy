package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.stages.StageType
import com.continuousx.jenkins.stages.StageConfig

class StageJenkinsConvertPluginsTxtConfig implements StageConfig {

    final static StageType type = StageType.STAGE_JENKINS_CONVERT_PLUGINS_TXT
    boolean active = true
    boolean failOnError = true

    LogLevelType logLevelType

    @Override
    @NonCPS
    StageType getType() {
        return type
    }

    @Override
    @NonCPS
    @SuppressWarnings('ClashingGetters')
    boolean isActive() {
        return active
    }

    @Override
    @SuppressWarnings('ClashingGetters')
    boolean isFailOnError() {
        return failOnError
    }

    @Override
    LogLevelType logLevelType() {
        return logLevelType
    }
}
