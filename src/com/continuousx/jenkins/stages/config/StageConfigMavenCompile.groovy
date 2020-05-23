package com.continuousx.jenkins.stages.config

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.stages.StageType
import com.continuousx.jenkins.stages.config.StageConfig

class StageConfigMavenCompile implements StageConfig, Serializable {

    private final static StageType stageType = StageType.STAGE_MAVEN_COMPILE
    boolean active = true
    boolean failOnError = true

    LogLevelType logLevelType

    @Override
    StageType getStageType() {
        return stageType
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

    @Override
    @NonCPS
    LogLevelType logLevelType() {
        return logLevelType
    }
}
