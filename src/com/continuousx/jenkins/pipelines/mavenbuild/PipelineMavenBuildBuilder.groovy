package com.continuousx.jenkins.pipelines.mavenbuild

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.AbstractPipeline

class PipelineMavenBuildBuilder extends AbstractPipeline {

    protected PipelineMavenBuildBuilder(def jenkinsContext, PipelineMavenBuildConfig config, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                config,
                ["workflow-basic-steps","maven-plugin"],
                logLevel)
    }

    @Override
    @NonCPS
    PipelineMavenBuildConfig getConfig() {
        return config
    }
}
