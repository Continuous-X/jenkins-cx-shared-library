package com.continuousx.jenkins.pipelines.mavenbuild

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.AbstractPipeline
import com.continuousx.jenkins.stages.Stage
import com.continuousx.jenkins.stages.jenkins.convertpluginstxt.StageJenkinsConvertPluginsTxtBuilder

class PipelineMavenBuildImpl extends AbstractPipeline {

    Stage stageJenkinsConvertPluginsTxt

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyVariableCanBeFinal'])
    protected PipelineMavenBuildImpl(
            def jenkinsContext,
            PipelineMavenBuildConfig config) {
        super(jenkinsContext,
                ["workflow-basic-steps", "maven-plugin"],
                config)
        stageJenkinsConvertPluginsTxt = new StageJenkinsConvertPluginsTxtBuilder(jenkinsContext)
                .withStageConfig(config.getStageJenkinsConvertPluginsTxtConfig())
                .build()
    }

    @Override
    @NonCPS
    void runPipelineImpl() {

    }

}
