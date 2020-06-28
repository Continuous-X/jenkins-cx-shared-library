package com.continuousx.jenkins.pipelines.mavenbuild

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipelines.AbstractPipeline
import com.continuousx.jenkins.stages.Stage
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileBuilder

class PipelineMavenBuildImpl extends AbstractPipeline {

    Stage stageMavenInstall

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyVariableCanBeFinal'])
    protected PipelineMavenBuildImpl(
            def jenkinsContext,
            PipelineMavenBuildConfig config) {
        super(jenkinsContext,
                ["workflow-basic-steps", "maven-plugin"],
                config)
        stageMavenInstall = new StageMavenCompileBuilder(jenkinsContext)
                .withStageConfig(config.getStageConfigMavenCompile())
                .build()
    }

    @Override
    @NonCPS
    void runPipelineImpl() {

    }

}
