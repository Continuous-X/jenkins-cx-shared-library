package com.continuousx.jenkins.pipelines.config

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageConfigJenkinsConvertPluginsTxt
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigMavenBuild implements PipelineConfig {
    private final static PipelineType pipelineType = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType
    List<StageConfig> stageList = [
            new StageConfigJenkinsConvertPluginsTxt(
                    active: true,
                    failOnError: true
            )
    ] as List<StageConfig>

    @Override
    PipelineType getPipelineType() {
        return pipelineType
    }

    @Override
    LogLevelType logLevelType() {
        return logLevelType
    }
}
