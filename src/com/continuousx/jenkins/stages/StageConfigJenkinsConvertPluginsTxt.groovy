package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType

class StageConfigJenkinsConvertPluginsTxt implements StageConfig {

    private final static StageType stageType = StageType.STAGE_JENKINS_CONVERT_PLUGINS_TXT
    boolean active = true
    boolean failOnError = true

    LogLevelType logLevelType

    @Override
    StageType getStageType() {
        return stageType
    }

    @Override
    boolean isActive() {
        return active
    }

    @Override
    boolean isFailOnError() {
        return failOnError
    }

    @Override
    LogLevelType logLevelType() {
        return logLevelType
    }
}
