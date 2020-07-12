package com.continuousx.jenkins.stages

import com.continuousx.utils.logger.Logger

interface Stage {

    StageConfig stageConfig
    List<String> neededPlugins
    Logger logger

    void runStage()
    void publishMetricOperating()

}
