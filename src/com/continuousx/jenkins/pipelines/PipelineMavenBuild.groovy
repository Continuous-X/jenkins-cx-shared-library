package com.continuousx.jenkins.pipelines

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.maven.MavenFeatureWrapperImpl
import com.continuousx.jenkins.pipelines.config.PipelineConfig
import com.continuousx.jenkins.pipelines.config.PipelineConfigMavenBuild
import com.continuousx.jenkins.stages.AbstractStage
import com.continuousx.jenkins.stages.config.StageConfigMavenCompile

class PipelineMavenBuild extends AbstractPipeline {
    PipelineMavenBuild(def jenkinsContext, PipelineConfigMavenBuild config, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                config,
                ["workflow-basic-steps","maven-plugin"],
                logLevel)
    }

    @Override
    PipelineConfigMavenBuild getConfig() {
        return config
    }
}
