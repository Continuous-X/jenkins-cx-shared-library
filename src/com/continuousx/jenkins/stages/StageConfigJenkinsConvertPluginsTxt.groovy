package com.continuousx.jenkins.stages

class StageConfigJenkinsConvertPluginsTxt implements StageConfig {

    private final static StageType stageType = StageType.STAGE_JENKINS_CONVERT_PLUGINS_TXT
    boolean active = true
    boolean failOnError = true

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
}
