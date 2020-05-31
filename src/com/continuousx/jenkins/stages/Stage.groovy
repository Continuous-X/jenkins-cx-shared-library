package com.continuousx.jenkins.stages

interface Stage {

    void runStage()
    StageConfig getConfig()

}
