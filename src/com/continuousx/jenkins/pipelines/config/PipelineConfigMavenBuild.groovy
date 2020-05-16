package com.continuousx.jenkins.pipelines.config

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.StageConfigJenkinsConvertPluginsTxt
import com.continuousx.jenkins.stages.StageType
import groovy.transform.TypeChecked

@TypeChecked
class PipelineConfigMavenBuild implements PipelineConfig {
    private final static PipelineType pipelineType = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType
    List<PipelineConfigStageConfigEntry> stageList = [
            new PipelineConfigStageConfigEntry(
                    name: StageType.STAGE_JENKINS_CONVERT_PLUGINS_TXT,
                    stageConfig: new StageConfigJenkinsConvertPluginsTxt(
                            active: true,
                            failOnError: true
                    )
            )
    ]

    @Override
    PipelineType getPipelineType() {
        return pipelineType
    }

    @Override
    LogLevelType logLevelType() {
        return logLevelType
    }
}
