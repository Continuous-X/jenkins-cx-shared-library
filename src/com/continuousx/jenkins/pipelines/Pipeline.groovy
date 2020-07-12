package com.continuousx.jenkins.pipelines

interface Pipeline {

    List<String> neededPlugins
    PipelineConfig pipelineConfig

    void publishMetricOperating()

}
