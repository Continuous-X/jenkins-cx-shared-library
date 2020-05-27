package com.continuousx.jenkins.pipelines.mavenbuild


import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.config.StageConfigJenkinsConvertPluginsTxt
import com.continuousx.jenkins.stages.config.StageConfigMavenCompile
import groovy.transform.TypeChecked

@TypeChecked
class PipelineMavenBuildConfig implements PipelineConfig {

    private final static PipelineType pipelineType = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType

    StageConfigJenkinsConvertPluginsTxt stageConfigJenkinsConvertPluginsTxt = new StageConfigJenkinsConvertPluginsTxt(
            active: true,
            failOnError: true
    )

    StageConfigMavenCompile stageConfigMavenCompile = new StageConfigMavenCompile(
            active: true,
            failOnError: true
    )

    PipelineMavenBuildConfig configStageJenkinsConvertPluginsTxt(boolean active, boolean failOnError) {
        stageConfigJenkinsConvertPluginsTxt.setActive(active)
        stageConfigJenkinsConvertPluginsTxt.setFailOnError(failOnError)
        return this
    }

    PipelineMavenBuildConfig configStageMavenCompile(boolean active, boolean failOnError) {
        stageConfigMavenCompile.setActive(active)
        stageConfigMavenCompile.setFailOnError(failOnError)
        return this
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
