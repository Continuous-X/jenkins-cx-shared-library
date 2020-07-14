package com.continuousx.jenkins.stages

interface Stage {

    StageConfig stageConfig
    List<String> neededPlugins

    void runStage()
    void publishMetricOperating()

}
