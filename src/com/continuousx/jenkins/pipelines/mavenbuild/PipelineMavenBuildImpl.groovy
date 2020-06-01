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
            PipelineMavenBuildConfig config,
            LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                config,
                ["workflow-basic-steps", "maven-plugin"],
                logLevel)
        stageJenkinsConvertPluginsTxt = new StageJenkinsConvertPluginsTxtBuilder(jenkinsContext)
                .withStageConfig(config.getStageJenkinsConvertPluginsTxtConfig())
                .build()
    }

    @Override
    @NonCPS
    PipelineMavenBuildConfig getConfig() {
        return config
    }

    @Override
    @NonCPS
    void runPipeline() {

    }

    void setOptions() {

        try {
            if (true) {
                jenkinsContext.options()
            }
        } catch (NoSuchMethodError ex) {
            jenkinsContext.log.error "Fehler beim Aufruf \n ${ex.message}"

        }
        try {
            if (true) {
                new hudson.plugins.timestamper.pipeline.TimestamperStep().start(jenkinsContext)
                jenkinsContext.timestamps()
            }
        } catch (Exception ex) {
            jenkinsContext.log.error "Fehler beim Aufruf \n ${ex.message}"

        }
    }
}
