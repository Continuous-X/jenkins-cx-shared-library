package com.continuousx.jenkins.pipelines

interface Pipeline {

    PipelineConfig getConfig()
    void runPipeline()
    void publishMetricOperating()

}
