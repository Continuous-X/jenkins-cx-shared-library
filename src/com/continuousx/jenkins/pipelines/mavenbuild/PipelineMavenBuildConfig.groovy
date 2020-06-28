package com.continuousx.jenkins.pipelines.mavenbuild

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileConfig
import groovy.transform.TypeChecked

@TypeChecked
class PipelineMavenBuildConfig implements PipelineConfig {

    final static PipelineType TYPE = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType = LogLevelType.INFO

    StageMavenCompileConfig stageConfigMavenCompile = new StageMavenCompileConfig(
            active: true,
            failOnError: true
    )

    PipelineMavenBuildConfig configStageMavenCompile(boolean active, boolean failOnError) {
        stageConfigMavenCompile.active = active
        stageConfigMavenCompile.failOnError = failOnError
        this
    }

    @Override
    @NonCPS
    PipelineType getType() {
        TYPE
    }

    @Override
    @NonCPS
    LogLevelType getLogLevelType() {
        logLevelType
    }
}
