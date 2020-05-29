package com.continuousx.jenkins.pipelines.mavenbuild

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.AbstractPipeline

class PipelineMavenBuildImpl extends AbstractPipeline {

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyVariableCanBeFinal'])
    protected PipelineMavenBuildImpl(
            def jenkinsContext,
            PipelineMavenBuildConfig config,
            LogLevelType logLevel = LogLevelType.INFO) {
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
