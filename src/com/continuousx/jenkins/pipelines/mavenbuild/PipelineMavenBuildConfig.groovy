package com.continuousx.jenkins.pipelines.mavenbuild

import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.github.protection.StageGithubProtectionCheckConfig
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileConfig
import groovy.transform.TypeChecked

@TypeChecked
class PipelineMavenBuildConfig implements PipelineConfig {

    final static PipelineType TYPE = PipelineType.PIPELINE_MAVEN_BUILD

    StageMavenCompileConfig stageConfigMavenCompile = new StageMavenCompileConfig()
    StageGithubProtectionCheckConfig stageGithubProtectionCheckConfig = new StageGithubProtectionCheckConfig()

    PipelineMavenBuildConfig configStageMavenCompile(boolean active, boolean failOnError) {
        stageConfigMavenCompile.active = active
        stageConfigMavenCompile.failOnError = failOnError
        stageConfigMavenCompile.logLevelType = logLevelType
        this
    }

    PipelineMavenBuildConfig configStageGHProtectionCheck(boolean active, boolean failOnError) {
        stageGithubProtectionCheckConfig.active = active
        stageGithubProtectionCheckConfig.failOnError = failOnError
        stageGithubProtectionCheckConfig.logLevelType = logLevelType
        this
    }

}
