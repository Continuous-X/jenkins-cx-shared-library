package com.continuousx.jenkins.pipelines.config

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.StageConfigJenkinsConvertPluginsTxt
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigMavenBuild implements PipelineConfig {
    private final static PipelineType pipelineType = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType
    private StageConfigJenkinsConvertPluginsTxt stageConfigJenkinsConvertPluginsTxt = new StageConfigJenkinsConvertPluginsTxt(
            active: true,
            failOnError: true,
            logLevelType: logLevelType
    )

    StageConfigJenkinsConvertPluginsTxt getStageConfigJenkinsConvertPluginsTxt() {
        return stageConfigJenkinsConvertPluginsTxt
    }

    void configStageJenkinsConvertPluginsTxt(boolean active, boolean failOnError) {
        stageConfigJenkinsConvertPluginsTxt.setActive(active)
        stageConfigJenkinsConvertPluginsTxt.setFailOnError(failOnError)
    }

    boolean isActive() {return false}

    @Override
    PipelineType getPipelineType() {
        return pipelineType
    }

    @Override
    LogLevelType logLevelType() {
        return logLevelType
    }
}
