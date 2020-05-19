package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.stages.config.StageConfig

abstract class AbstractStage {
    def jenkinsContext
    StageConfig config
    LogLevelType logLevel

    AbstractStage(def jenkinsContext, StageConfig config, LogLevelType logLevel = LogLevelType.INFO) {
        Objects.nonNull(jenkinsContext)
        Objects.nonNull(config)
        this.jenkinsContext = jenkinsContext
        this.config = config
        this.logLevel = logLevel
    }

    abstract void run()
}
