package com.continuousx.jenkins.pipelines.nextlevel


import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.jenkins.convertplugintxt.StageConfigJenkinsConvertPluginsTxt
import com.continuousx.jenkins.stages.maven.install.StageConfigMavenCompile
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigNextLevel implements PipelineConfig {
    private final static PipelineType pipelineType = PipelineType.PIPELINE_NEXT_LEVEL
    LogLevelType logLevelType

    StageConfigJenkinsConvertPluginsTxt stageConfigJenkinsConvertPluginsTxt = new StageConfigJenkinsConvertPluginsTxt(
            active: true,
            failOnError: true
    )

    StageConfigMavenCompile stageConfigMavenCompile = new StageConfigMavenCompile(
            active: true,
            failOnError: true
    )

    void configStageJenkinsConvertPluginsTxt(boolean active, boolean failOnError) {
        stageConfigJenkinsConvertPluginsTxt.setActive(active)
        stageConfigJenkinsConvertPluginsTxt.setFailOnError(failOnError)
    }

    void configStageMavenCompile(boolean active, boolean failOnError) {
        stageConfigMavenCompile.setActive(active)
        stageConfigMavenCompile.setFailOnError(failOnError)
    }

    @Override
    PipelineType getType() {
        return pipelineType
    }

    @Override
    LogLevelType getLogLevelType() {
        return logLevelType
    }
}
