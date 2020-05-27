package com.continuousx.jenkins.pipelines.mavenbuild

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.AbstractPipeline

class PipelineMavenBuild extends AbstractPipeline {
    PipelineMavenBuild(def jenkinsContext, PipelineMavenBuildConfig config, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                config,
                ["workflow-basic-steps","maven-plugin"],
                logLevel)
    }

    @Override
    PipelineMavenBuildConfig getConfig() {
        return config
    }
}
