package com.continuousx.jenkins.pipelines.mavenbuild

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.AbstractPipeline

class PipelineMavenBuildImpl extends AbstractPipeline {

    @SuppressWarnings('GroovyUntypedAccess')
    PipelineMavenBuildImpl(final def jenkinsContext, final PipelineMavenBuildConfig config, final LogLevelType logLevel = LogLevelType.INFO) {
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
