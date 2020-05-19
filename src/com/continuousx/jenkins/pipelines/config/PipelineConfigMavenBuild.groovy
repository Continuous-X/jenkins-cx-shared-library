package com.continuousx.jenkins.pipelines.config

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.config.StageConfigJenkinsConvertPluginsTxt
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigMavenBuild implements PipelineConfig {
    private final static PipelineType pipelineType = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType

    StageConfigJenkinsConvertPluginsTxt stageConfigJenkinsConvertPluginsTxt = new StageConfigJenkinsConvertPluginsTxt(
            active: true,
            failOnError: true
    )

    void configStageJenkinsConvertPluginsTxt(boolean active, boolean failOnError) {
        stageConfigJenkinsConvertPluginsTxt.setActive(active)
        stageConfigJenkinsConvertPluginsTxt.setFailOnError(failOnError)
    }

    @Override
    PipelineType getPipelineType() {
        return pipelineType
    }

    @Override
    LogLevelType getLogLevelType() {
        return logLevelType
    }
}
