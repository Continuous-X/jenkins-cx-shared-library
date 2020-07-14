package com.continuousx.jenkins.stages

interface Stage {

    List<String> neededPlugins
    StageConfig stageConfig

    void runStage()
    void publishMetricOperating()

}
