package com.continuousx.jenkins.pipelines.mavenbuild


import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.jenkins.convertpluginstxt.StageJenkinsConvertPluginsTxtConfig
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileConfig
import groovy.transform.TypeChecked

@TypeChecked
class PipelineMavenBuildConfig implements PipelineConfig {

    final static PipelineType type = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType

    StageJenkinsConvertPluginsTxtConfig stageJenkinsConvertPluginsTxtConfig = new StageJenkinsConvertPluginsTxtConfig(
            active: true,
            failOnError: true
    )

    StageMavenCompileConfig stageConfigMavenCompile = new StageMavenCompileConfig(
            active: true,
            failOnError: true
    )

    PipelineMavenBuildConfig configStageJenkinsConvertPluginsTxt(boolean active, boolean failOnError) {
        stageJenkinsConvertPluginsTxtConfig.active = active
        stageJenkinsConvertPluginsTxtConfig.failOnError = failOnError
        return this
    }

    PipelineMavenBuildConfig configStageMavenCompile(boolean active, boolean failOnError) {
        stageConfigMavenCompile.active = active
        stageConfigMavenCompile.failOnError = failOnError
        return this
    }

    @Override
    PipelineType getType() {
        return type
    }

    @Override
    LogLevelType getLogLevelType() {
        return logLevelType
    }
}
