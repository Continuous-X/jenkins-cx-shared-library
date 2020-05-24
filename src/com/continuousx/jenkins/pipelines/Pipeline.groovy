package com.continuousx.jenkins.pipelines

import com.continuousx.jenkins.pipelines.config.PipelineConfig

interface Pipeline {
    PipelineConfig getConfig()
}